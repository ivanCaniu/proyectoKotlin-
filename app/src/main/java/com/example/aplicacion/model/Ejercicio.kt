package com.example.aplicacion.model

import androidx.annotation.DrawableRes

data class Ejercicio(
    val id: String,
    val name: String,
    val description: String,
    val muscleGroup: String,
    @DrawableRes val imageRes: Int,
    val detailedDescription: String
)

data class Rutina(
    val nombre: String,
    val ejercicios: List<Ejercicio>
)
