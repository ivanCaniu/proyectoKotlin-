package com.example.aplicacion.model

import androidx.annotation.DrawableRes

data class Aliemento(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val detailedDescription: String
)