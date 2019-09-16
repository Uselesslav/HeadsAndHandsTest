package com.example.headsandhandstest.authorization.infrastracture

import com.example.headsandhandstest.authorization.application.WeatherRepository
import com.google.gson.Gson
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class WeatherRepositoryImplementation(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: String,
    private val token: String,
    // TODO: Use wrapper
    private val gson: Gson
) : WeatherRepository {
    companion object {
        private const val tokenKey = "key"
        private const val queryKey = "q"
        private const val queryValue = "Moscow"
    }

    override suspend fun get(): String =
        parseResponse(requestWeather(baseUrl, mapOf(tokenKey to token, queryKey to queryValue)))

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

    private fun parseResponse(response: String): String {
        val result = gson.fromJson(response, WeatherDto::class.java)

        return result.location.name + " " + result.current.temp_c
    }
}