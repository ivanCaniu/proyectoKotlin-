package com.example.aplicacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.data.remote.MealApiService
import com.example.aplicacion.model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Estado de la pantalla de lista de comidas
data class MealState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class MealViewModel : ViewModel() {

    private val _state = MutableStateFlow(MealState())
    val state: StateFlow<MealState> = _state.asStateFlow()

    private val apiService: MealApiService

    init {
        // Inicializar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MealApiService::class.java)

        // Cargar las comidas al iniciar el ViewModel
        fetchMeals()
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            _state.value = MealState(isLoading = true)
            try {
                val response = apiService.getMeals()
                _state.value = MealState(meals = response.meals)
            } catch (e: Exception) {
                _state.value = MealState(error = "Error al cargar las comidas: ${e.message}")
            }
        }
    }
}