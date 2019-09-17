package com.example.headsandhandstest

import com.example.headsandhandstest.authorization.application.Weather
import com.example.headsandhandstest.authorization.infrastracture.*
import org.junit.Assert
import org.junit.Test

class WeatherConverterUnitTest {
    private val weatherConverter = WeatherConverter()

    companion object {
        private const val LOCATION_NAME = "Loc name"
        private const val CURRENT_TEMPERATURE = 9
    }

    @Test
    fun fromDto() {
        val dto = WeatherDto(
            LocationDto(
                LOCATION_NAME,
                "mo",
                "russ",
                100.0,
                200.0,
                "frt",
                12,
                "we"
            ),
            CurrentDto(
                13,
                "last",
                CURRENT_TEMPERATURE,
                1.1,
                0,
                ConditionDto("tes", "ico", 102),
                44.4,
                33.3,
                83,
                "qwer",
                22,
                24.4,
                55.5,
                654.5,
                34,
                6,
                42.7,
                73.45,
                62,
                41,
                32,
                53.4,
                92.5
            )
        )

        val actual = weatherConverter.fromDto(dto)
        val expected = Weather(LOCATION_NAME, CURRENT_TEMPERATURE)

        Assert.assertEquals(expected, actual)
    }
}