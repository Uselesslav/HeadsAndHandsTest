package com.example.headsandhandstest.kernel.infrastructure

import okhttp3.Interceptor
import okhttp3.Response

class ConnectionCheckInterceptor(private val connectionManager: ConnectionManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        connectionManager.checkConnection()

        return chain.proceed(chain.request())
    }
}