package com.example.aplicacion.model

import androidx.annotation.DrawableRes

data class Ejercicio(
    val id: String,
    val name: String,
    val description: String,
    val muscleGroup: String,
    @DrawableRes val imageRes: Int, // Propiedad para la imagen
    val detailedDescription: String
)
