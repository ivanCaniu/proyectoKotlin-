package com.example.aplicacion.network

import com.example.aplicacion.model.auth.LoginDTO
import com.example.aplicacion.model.auth.LoginResponseDTO
import com.example.aplicacion.model.auth.RegistroDTO
import com.example.aplicacion.model.auth.UsuarioResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth/register")
    suspend fun register(@Body registroDTO: RegistroDTO): Response<UsuarioResponseDTO>

    @POST("api/auth/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<LoginResponseDTO>

}
