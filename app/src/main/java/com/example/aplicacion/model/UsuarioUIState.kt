package com.example.aplicacion.model

data class UsuarioUIState(
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val contraseña: String = "",
    val confirmarContraseña: String = "",
    val aceptaTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores(),
    val isLoading: Boolean = false
)
