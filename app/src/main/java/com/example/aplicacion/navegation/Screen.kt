package com.example.aplicacion.navegation

sealed class Screen(val route: String) {

    data object Home : Screen(route = "home")
    data object Registro : Screen(route = "registro")
    data object Login : Screen(route = "login")

    data object Profile : Screen(route = "profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }
}
