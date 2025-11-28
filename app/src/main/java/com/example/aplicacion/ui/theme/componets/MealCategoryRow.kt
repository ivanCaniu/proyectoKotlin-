package com.example.aplicacion.ui.theme.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aplicacion.model.CustomMealInfo
import com.example.aplicacion.ui.theme.screen.MealCard

@Composable
fun MealCategoryRow(
    categoryName: String,
    meals: List<CustomMealInfo>,
    modifier: Modifier = Modifier
){
    Column(modifier) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(meals){ meal ->
                MealCard(meal = meal,modifier = Modifier.width(200.dp))

            }
        }


    }
}