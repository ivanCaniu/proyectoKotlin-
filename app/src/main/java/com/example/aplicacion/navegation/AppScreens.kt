package com.example.aplicacion.navegation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : AppScreens(
        route = "home",
        title = "Inicio",
        icon = Icons.Default.Home
    )

    object Foods : AppScreens(
        route = "foods",
        title = "Alimentos",
        icon = Icons.Default.Fastfood
    )

    object Profile : AppScreens(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}