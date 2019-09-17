package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.Weather
import com.example.headsandhandstest.authorization.application.WeatherRepository

class WeatherRepositoryMockImplementation(private val response: Weather) : WeatherRepository {
    override suspend fun get(): Weather = response
}