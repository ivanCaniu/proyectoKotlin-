package com.example.aplicacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.model.MealScreenState
import com.example.aplicacion.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class MealViewModel : ViewModel() {

    private val _state = MutableStateFlow(MealScreenState())
    val state = _state.asStateFlow()

    init {
        val categoriesToFetch = listOf("Seafood", "Breakfast", "Chicken", "Dessert", "Pasta")
        fetchCategoriesProgressively(categoriesToFetch)
    }


    private fun fetchCategoriesProgressively(categories: List<String>) {
        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, error = null, groupedMeals = emptyMap()) }

            launch(Dispatchers.IO) {
                try {

                    categories.forEach { categoryName ->

                        val mealListResponse = RetrofitClient.mealApiService.getMealsByCategory(categoryName)
                        val mealsFromApi = mealListResponse.meals.take(8)


                        val detailedMeals = mealsFromApi.map { meal ->
                            async {
                                RetrofitClient.mealApiService.getMealDetails(meal.id)
                            }
                        }.awaitAll()


                        val customMealInfos = detailedMeals.mapNotNull { response ->
                            response.meals.firstOrNull()?.toCustomMealInfo()
                        }


                        val newGroup = customMealInfos.groupBy { it.customCategory }


                        _state.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                groupedMeals = currentState.groupedMeals + newGroup // Añadimos el nuevo grupo
                            )
                        }
                    }
                } catch (e: Exception) {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error al obtener las recetas: ${e.message}"
                        )
                    }
                }
            }
        }
    }

    fun getMealDetails(mealId: String) {
        viewModelScope.launch {
            // 1. Pone la UI en estado de carga y limpia datos/errores anteriores.
            _state.update { it.copy(isLoading = true, error = null, selectedMeal = null) }

            try {
                // 2. Ejecuta la llamada de red en un hilo de fondo (IO).
                val mealDetailsResponse = withContext(Dispatchers.IO) {
                    RetrofitClient.mealApiService.getMealDetails(mealId)
                }

                // 3. Extrae el primer (y único) detalle de la lista y lo convierte.
                val mealDetail = mealDetailsResponse.meals.firstOrNull()?.toCustomMealInfo()

                // 4. Actualiza el estado con el detalle de la comida y finaliza la carga.
                _state.update {
                    it.copy(
                        isLoading = false,
                        selectedMeal = mealDetail
                    )
                }
            } catch (e: Exception) {
                // 5. En caso de error, actualiza el estado con un mensaje.
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar los detalles: ${e.message}"
                    )
                }
            }
        }
    }
}

