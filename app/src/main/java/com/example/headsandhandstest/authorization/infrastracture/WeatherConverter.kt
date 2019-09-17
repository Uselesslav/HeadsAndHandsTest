package com.example.headsandhandstest.authorization.infrastracture

import com.example.headsandhandstest.authorization.application.Weather

class WeatherConverter {
    fun fromDto(weatherDto: WeatherDto): Weather =
        Weather(weatherDto.location.name, weatherDto.current.temp_c)
}