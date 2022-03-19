package com.resurrection.base.component

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.resurrection.base.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class NetworkManager @Inject constructor(
    @ApplicationContext val context: Context,
    val typeConverter: TypeConverter
) {
    private var _okHttpClient = OkHttpClient().newBuilder().build()
    val okHttpClient = _okHttpClient
    private var baseUrl = ""
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    private var _isNetworkAvailable = checkNetworkAvailable()
    val isNetworkAvailable: Boolean = _isNetworkAvailable

    companion object {
        const val GET = "GET"
        const val POST = "POST"
        const val PUT = "PUT"
        const val DELETE = "DELETE"
    }

    fun init(okHttpClient: OkHttpClient = _okHttpClient,baseUrl: String) {
        this.baseUrl = baseUrl
        this._okHttpClient = okHttpClient
    }

    fun <T> newRequest(
        okHttpClient: OkHttpClient = this.okHttpClient,
        baseUrl: String = this.baseUrl,
        method: String = GET,
        body: String? = null,
        headers: Map<String, String>? = null,
        path: String,
        responseType: Class<T>
    ) = flow {
        val requestBuilder = Request.Builder()
            .url(baseUrl + path)
            .method(method, body?.toRequestBody())

        headers?.let { requestBuilder.headers(headers.toHeaders()) }

        val request = requestBuilder.build()

        var resultResource: Resource<T>? = null
        try {
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.apply {
                    resultResource =
                        Resource.Success(typeConverter.fromJson(this.string(), responseType))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            resultResource = Resource.Error(e)
        }
        if (resultResource == null) resultResource = Resource.Loading()

        emit(resultResource!!)
    }.flowOn(Dispatchers.IO)

    fun checkNetworkAvailable(): Boolean =
        try {
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> true
                }
            } else false
        } catch (e: Exception) {
            false
        }

    fun isVpnActive() = capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_VPN)

    @RequiresApi(Build.VERSION_CODES.N)
    fun setNetworkStateListener(available: () -> Unit, unAvailable: () -> Unit) {

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isNetworkAvailable = true
                available()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _isNetworkAvailable = false
                unAvailable()
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                _isNetworkAvailable = false
                unAvailable()
            }

            override fun onUnavailable() {
                super.onUnavailable()
                _isNetworkAvailable = false
                unAvailable()
            }

        })
    }

}

