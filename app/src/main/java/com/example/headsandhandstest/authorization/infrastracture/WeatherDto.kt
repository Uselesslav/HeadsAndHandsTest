package com.example.headsandhandstest.authorization.infrastracture

data class WeatherDto(
    val location: LocationDto,
    val current: CurrentDto
)