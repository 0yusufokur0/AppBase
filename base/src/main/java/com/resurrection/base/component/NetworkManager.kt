package com.resurrection.base.component

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkManager @Inject constructor(@ApplicationContext val context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    private var _isNetworkAvailable = checkNetworkAvailable()
    val isNetworkAvailable: Boolean = _isNetworkAvailable

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

