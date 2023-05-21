package com.example.retrofitsimple.models

import java.io.Serializable

data class WeatherResponse(
    val coord: Coord,
    val weather : List<Weather>,
    val main: Main,
    val visibility : Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt:Int,
    val sys: Sys,
    val id:Int,
    val name:String,
    val cod:Int
): Serializable