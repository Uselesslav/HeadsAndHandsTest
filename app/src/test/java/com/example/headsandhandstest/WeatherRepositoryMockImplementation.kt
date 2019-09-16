package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.WeatherRepository

class WeatherRepositoryMockImplementation(private val response: String) : WeatherRepository {
    override suspend fun get(): String = response
}