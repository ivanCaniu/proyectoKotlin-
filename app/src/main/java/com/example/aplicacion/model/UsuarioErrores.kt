package com.example.aplicacion.model

data class UsuarioErrores(
    val nombre: String? = null,
    val apellido: String? = null,
    val email: String? = null,
    val contraseña: String? = null,
    val confirmarContraseña: String? = null,
    val aceptaTerminos: String? = null
)
