package com.example.aplicacion.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacion.data.listaDeEjercicios
import com.example.aplicacion.navegation.Screen
import com.example.aplicacion.ui.theme.componets.AppDrawer
import com.example.aplicacion.ui.theme.componets.EjercicioCard
import com.example.aplicacion.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    AppDrawer(
        viewModel = viewModel,
        currentRoute = Screen.Home.route
    ) { openDrawer ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Guía de Ejercicios") },
                    navigationIcon = {
                        IconButton(onClick = openDrawer) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = listaDeEjercicios,
                    key = { it.id }
                ) { ejercicio ->
                    EjercicioCard(ejercicio = ejercicio, navController = navController)
                }
            }
        }
    }
}