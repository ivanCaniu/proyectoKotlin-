package com.example.aplicacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.model.auth.LoginDTO
import com.example.aplicacion.model.auth.RegistroDTO
import com.example.aplicacion.network.AuthRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Estado para la UI, para comunicar éxito, error o carga.
data class AuthState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)

class UsuarioViewModel : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    fun register(registroDTO: RegistroDTO) {
        viewModelScope.launch {
            // 1. Poner la UI en estado de carga
            _authState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }

            try {
                // 2. Ejecutar la llamada a la API en un hilo de fondo
                val response = withContext(Dispatchers.IO) {
                    AuthRetrofitClient.authApiService.register(registroDTO)
                }

                // 3. Procesar la respuesta
                if (response.isSuccessful) {
                    _authState.update {
                        it.copy(isLoading = false, successMessage = "¡Registro exitoso! Ahora puedes iniciar sesión.")
                    }
                } else {
                    // El servidor devolvió un error (ej. email ya en uso)
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    _authState.update { it.copy(isLoading = false, errorMessage = errorBody) }
                }
            } catch (e: Exception) {
                // Error de red (ej. no se puede conectar al servidor)
                _authState.update { it.copy(isLoading = false, errorMessage = "Error de conexión: ${e.message}") }
            }
        }
    }

    fun login(loginDTO: LoginDTO) {
        viewModelScope.launch {
            // 1. Poner la UI en estado de carga
            _authState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }

            try {
                // 2. Ejecutar la llamada a la API en un hilo de fondo
                val response = withContext(Dispatchers.IO) {
                    AuthRetrofitClient.authApiService.login(loginDTO)
                }

                // 3. Procesar la respuesta
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    _authState.update {
                        it.copy(isLoading = false, successMessage = loginResponse?.message ?: "Login exitoso")
                    }
                    // Aquí, en un futuro, guardarías el 'token' de forma segura
                } else {
                    // El servidor devolvió un error (ej. contraseña incorrecta)
                    _authState.update { it.copy(isLoading = false, errorMessage = "Email o contraseña incorrectos.") }
                }
            } catch (e: Exception) {
                // Error de red
                _authState.update { it.copy(isLoading = false, errorMessage = "Error de conexión: ${e.message}") }
            }
        }
    }
}

