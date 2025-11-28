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
        fetchAllCategories(categoriesToFetch)
    }

    private fun fetchAllCategories(categories: List<String>) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }


            val result = withContext(Dispatchers.IO) {
                try {

                    val categoryResponses = categories.map { categoryName ->
                        async {
                            RetrofitClient.mealApiService.getMealsByCategory(categoryName)
                        }
                    }.awaitAll()

                    val allMealsFromApi = categoryResponses.flatMap { it.meals }

                    val detailedMealsDeferred = allMealsFromApi.map { meal ->
                        async {
                            RetrofitClient.mealApiService.getMealDetails(meal.id)
                        }
                    }
                    val detailedResponses = detailedMealsDeferred.awaitAll()

                    val customMealInfos = detailedResponses.mapNotNull { response ->
                        response.meals.firstOrNull()?.toCustomMealInfo()
                    }

                    val grouped = customMealInfos.groupBy { it.customCategory }

                    // resultado exitoso
                    Result.success(grouped)

                } catch (e: Exception) {
                    //  error
                    Result.failure(e)
                }
            }


            result.onSuccess { groupedMeals ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        groupedMeals = groupedMeals,
                        error = null
                    )
                }
            }.onFailure { exception ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al obtener las recetas: ${exception.message}"
                    )
                }
            }
        }
    }
}

