package com.example.aplicacion.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplicacion.viewmodel.MealViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailScreen(
    mealId: String,                      // El ID de la comida que recibimos de la navegación
    mealViewModel: MealViewModel = viewModel(), // Instancia del ViewModel
    onNavigateBack: () -> Unit           // Función para volver a la pantalla anterior
) {
    // 1. Observamos el estado del ViewModel. La UI se reconstruirá si cambia.
    val state by mealViewModel.state.collectAsState()
    val meal = state.selectedMeal // El detalle de la comida que hemos cargado

    // 2. Usamos LaunchedEffect para llamar a la API una sola vez cuando la pantalla se crea.
    // Se ejecuta cada vez que el `mealId` cambia.
    LaunchedEffect(mealId) {
        mealViewModel.getMealDetails(mealId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(meal?.name ?: "Detalle de Comida") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // 3. Manejo de los diferentes estados de la UI
            when {
                // Si está cargando, mostramos un indicador de progreso
                state.isLoading -> {
                    CircularProgressIndicator()
                }
                // Si hay un error, lo mostramos
                state.error != null -> {
                    Text(
                        text = state.error!!,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                // Si la comida ya se cargó, mostramos los detalles
                meal != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            // Imagen de la comida
                            AsyncImage(
                                model = meal.imageUrl,
                                contentDescription = meal.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // Nombre de la comida
                            Text(
                                text = meal.name,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            // Categoría y Área (si los tienes en CustomMealInfo)
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Categoría: ${meal.customCategory}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(16.dp))

                            // Instrucciones
                            Text(
                                text = "Instrucciones",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = meal.instructions,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}