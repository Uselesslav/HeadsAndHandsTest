package com.example.headsandhandstest.authorization.application

interface WeatherRepository {
    suspend fun get(): String
}