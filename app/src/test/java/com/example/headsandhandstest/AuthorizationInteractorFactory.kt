package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.AuthorizationInteractor
import com.example.headsandhandstest.authorization.application.Weather

class AuthorizationInteractorFactory {
    fun create(mockResponse: Weather = Weather("Lalala", 12)) = AuthorizationInteractor(
        WeatherRepositoryMockImplementation(mockResponse),
        SignInDtoValidatorFactory().create()
    )
}