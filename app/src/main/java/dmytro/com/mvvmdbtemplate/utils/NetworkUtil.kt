package dmytro.com.mvvmdbtemplate.utils

import android.content.Context
import android.net.ConnectivityManager

    fun isInternetAvailable(context: Context): Boolean {
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectionManager.activeNetworkInfo
        return if (activeNetwork != null) {
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> { true }
                ConnectivityManager.TYPE_MOBILE -> { true }
                else -> { false }
            }
        } else {
            false
        }
    }