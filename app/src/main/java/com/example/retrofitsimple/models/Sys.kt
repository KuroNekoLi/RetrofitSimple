package com.example.retrofitsimple.models
import java.io.Serializable

data class Sys(
    val type:Int,
    val id:Long,
    val country:String,
    val sunrise:Long,
    val sunset:Long
):java.io.Serializable
