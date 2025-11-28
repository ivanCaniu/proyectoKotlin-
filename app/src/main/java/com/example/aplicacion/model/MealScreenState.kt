package com.example.aplicacion.model

data class MealScreenState(
    val isLoading: Boolean = false,
    val groupedMeals: Map<String, List<CustomMealInfo>> = emptyMap(),
    val error: String? = null

)

data class MealState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)