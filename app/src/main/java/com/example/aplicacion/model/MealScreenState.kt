package com.example.aplicacion.model

data class MealScreenState(
    val isLoading: Boolean = false,
    val meals: List<Meal> = emptyList(),
    val error: String? = null

)