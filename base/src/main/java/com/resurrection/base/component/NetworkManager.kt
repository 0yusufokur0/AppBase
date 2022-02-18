package com.resurrection.base.component

import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkManager @Inject constructor(@ApplicationContext val context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    fun isNetworkAvailable(): Boolean {
        return try {
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> true
                }
            }else false
        }catch (e:Exception){ false }
    }

    fun isVpnActive() = capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_VPN)

}