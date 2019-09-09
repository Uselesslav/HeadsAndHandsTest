package com.example.headsandhandstest.authorization.infrastracture

import com.example.headsandhandstest.authorization.application.WeatherRepository
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class WeatherRepositoryImplementation(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: String,
    private val token: String
) : WeatherRepository {
    companion object {
        private const val tokenKey = "key"
        private const val queryKey = "q"
        private const val queryValue = "Moscow"
    }

    override suspend fun get(): String =
        requestWeather(baseUrl, mapOf(tokenKey to token, queryKey to queryValue))

    @Throws(IOException::class)
    private fun requestWeather(
        url: String,
        queries: Map<String, String>
    ): String {
        val urlBuilder = url.toHttpUrlOrNull()!!.newBuilder()
        queries.forEach {
            urlBuilder.addQueryParameter(it.key, it.value)
        }
        val httpUrl = urlBuilder.build().toString()

        val request = Request.Builder()
            .url(httpUrl)
            .build()

        return okHttpClient.newCall(request).execute().body!!.string()
    }
}