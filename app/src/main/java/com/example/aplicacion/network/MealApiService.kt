package com.example.aplicacion.network

import com.example.aplicacion.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealsResponse

}