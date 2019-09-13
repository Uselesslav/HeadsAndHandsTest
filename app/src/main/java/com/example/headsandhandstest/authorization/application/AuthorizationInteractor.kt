package com.example.headsandhandstest.authorization.application

class AuthorizationInteractor(
    private val weatherRepository: WeatherRepository,
    private val signInDtoValidator: SignInDtoValidator
) {
    suspend fun signIn(signInDto: SignInDto): String {
        signInDtoValidator.validate(signInDto)
        return weatherRepository.get()
    }
}