package com.example.aplicacion.model.auth

data class RegistroDTO(
    val nombre: String,
    val email: String,
    val contraseña: String
)

data class LoginDTO(
    val email: String,
    val contraseña: String
)

data class UsuarioResponseDTO(
    val id: Long,
    val nombre: String,
    val email: String
)

data class LoginResponseDTO(
    val mensaje: String,
    val token: String
)