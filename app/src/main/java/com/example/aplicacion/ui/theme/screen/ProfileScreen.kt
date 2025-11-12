package com.example.aplicacion.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aplicacion.navegation.Screen
import com.example.aplicacion.ui.theme.componets.AppDrawer
import com.example.aplicacion.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: MainViewModel, userId: String) {
    AppDrawer(
        viewModel = viewModel,
        currentRoute = Screen.Profile.createRoute(userId) //
    ) { openDrawer ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Perfil de Usuario") },
                    navigationIcon = {
                        IconButton(onClick = openDrawer) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Text("Contenido de la pantalla de perfil para el usuario $userId")
            }
        }
    }
}