package com.example.aplicacion.ui.theme.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplicacion.model.CustomMealInfo
import com.example.aplicacion.ui.theme.componets.MealCategoryRow
import com.example.aplicacion.viewmodel.MealViewModel

@Composable
fun MealListScreen(mealViewModel: MealViewModel = viewModel(),onMealClick: (String) -> Unit) {
    val state by mealViewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        else if (state.error != null) {
            Text(
                text = state.error!!,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }

        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                state.groupedMeals.forEach { (category, meals) ->
                    item {
                        MealCategoryRow(
                            categoryName = category,
                            meals = meals,
                            onMealClick = onMealClick
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun MealCard(meal: CustomMealInfo, modifier: Modifier = Modifier,
             onCLick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onCLick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
       
        Column {
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = meal.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

           
            Column(Modifier.padding(12.dp)) {
                Text(
                    text = meal.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1 
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${meal.calories} kcal", fontSize = 14.sp, color = Color.Gray)
                Text(text = "${meal.prepTimeMinutes} min", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}