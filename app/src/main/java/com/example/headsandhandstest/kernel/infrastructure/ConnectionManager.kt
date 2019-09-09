package com.example.headsandhandstest.kernel.infrastructure

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

// TODO: Remove deprecated methods
class ConnectionManager(private val context: Context) {
    private val networkInfo: NetworkInfo?
        get() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

    fun checkConnection() {
        if (!isConnected) {
            throw NoConnectionException()
        }
    }

    private val isConnected: Boolean
        get() = networkInfo?.isConnected == true
}