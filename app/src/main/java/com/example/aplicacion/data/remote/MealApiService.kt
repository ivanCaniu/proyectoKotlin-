package com.example.aplicacion.data.remote

import com.example.aplicacion.model.MealsResponse
import retrofit2.http.GET

interface MealApiService {
    // Endpoint para buscar comidas por la primera letra (en este caso, 'a')
    @GET("search.php?f=a")
    suspend fun getMeals(): MealsResponse
}
