package com.example.headsandhandstest.authorization.infrastracture

import com.example.headsandhandstest.authorization.application.WeatherRepository
import kotlinx.coroutines.delay

class WeatherRepositoryImplementation : WeatherRepository {
    override suspend fun get(): String {
        delay(1000)
        return "fsdfnsdfkjklmsdlk"
    }
}