package com.example.aplicacion.model

import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals") val meals: List<Meal>
)


data class Meal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val imagelUrl: String
)

data class MealDetailsResponse(
    val meals: List<MealDetails>
)


data class MealDetails(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strMealThumb") val imageUrl: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strTags") val tags: String?,

    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,

    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,


) {

    fun toCustomMealInfo(): CustomMealInfo {



        val calories = (300..800).random()
        val prepTime = (15..60).random()

        // 2. Determinar la categoría personalizada
        val customCategory = when {
            tags?.contains("Dessert", ignoreCase = true) == true -> "Con Azúcar"
            tags?.contains("Cake", ignoreCase = true) == true -> "Con Azúcar"
            tags?.contains("Sweet", ignoreCase = true) == true -> "Con Azucar"
            name.contains("Chocolate", ignoreCase = true) -> "Con Azucar"

            category.equals("Breakfast", ignoreCase = true) -> "Desayuno"

            category.equals("Chicken", ignoreCase = true) -> "Almuerzo"
            category.equals("Beef", ignoreCase = true) -> "Almuerzo"
            category.equals("Pasta", ignoreCase = true) -> "Almuerzo"

            category.equals("Seafood", ignoreCase = true) -> "Cena"
            category.equals("Vegetarian", ignoreCase = true) -> "Cena"

            else -> "Sin Azúcar"
        }

        // 3. Juntar los ingredientes en una sola lista
        val ingredients = listOfNotNull(
            strIngredient1?.takeIf { it.isNotBlank() }?.let { "$it - ${strMeasure1 ?: ""}" },
            strIngredient2?.takeIf { it.isNotBlank() }?.let { "$it - ${strMeasure2 ?: ""}" },
            strIngredient3?.takeIf { it.isNotBlank() }?.let { "$it - ${strMeasure3 ?: ""}" }
            // ... podrías seguir hasta 20
        ).joinToString("\n")

        return CustomMealInfo(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl,
            calories = calories,
            prepTimeMinutes = prepTime,
            customCategory = customCategory,
            ingredients = ingredients,
            instructions = this.instructions
        )
    }
}



data class CustomMealInfo(
    val id: String,
    val name: String,
    val imageUrl: String,
    val calories: Int,
    val prepTimeMinutes: Int,
    val customCategory: String,
    val ingredients: String,
    val instructions: String
)

