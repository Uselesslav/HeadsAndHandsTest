package com.example.headsandhandstest.kernel.infrastructure

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

internal class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("REQUEST", "==============================")
        Log.d("METHOD", request.method)
        Log.d("URL", request.url.toString())
        Log.d("HEADERS", request.headers.toString())
        Log.d("BODY", request.body.toString())

        val response = chain.proceed(request)

        Log.d("RESPONSE", "==============================")
        Log.d("METHOD", request.method)
        Log.d("URL", request.url.toString())
        Log.d("HEADERS", response.headers.toString())

        return response
    }
}
