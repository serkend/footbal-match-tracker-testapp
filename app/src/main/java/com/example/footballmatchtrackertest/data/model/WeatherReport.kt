package com.example.footballmatchtrackertest.data.model

data class WeatherReport(
    val desc: String,
    val humidity_percent: Int,
    val pressure: Int,
    val temp: Temp,
    val wind: Wind
)