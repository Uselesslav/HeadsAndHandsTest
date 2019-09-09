package com.example.headsandhandstest.authorization.application

// TODO: Can be moved to another module
interface WeatherRepository {
    suspend fun get(): String
}