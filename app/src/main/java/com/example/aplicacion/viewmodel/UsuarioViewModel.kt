package com.example.aplicacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.model.UsuarioErrores
import com.example.aplicacion.model.UsuarioUIState
import com.example.aplicacion.navegation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {

    private val _estado = MutableStateFlow(value = UsuarioUIState())
    val estado: StateFlow<UsuarioUIState> = _estado

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onRegistroClick() {
        viewModelScope.launch {
            _estado.update { it.copy(isLoading = true) }
            delay(1000)
            val hayError = validarFormulario()
            if (!hayError) {
                _navigationEvent.emit(Screen.Home.route)
            }
            _estado.update { it.copy(isLoading = false) }
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _estado.update { it.copy(isLoading = true) }
            delay(1000)
            val hayErrorDeFormato = validarLogin()
            if (!hayErrorDeFormato) {
                _navigationEvent.emit(Screen.Home.route)
            }
            _estado.update { it.copy(isLoading = false) }
        }
    }

    fun onNombreChanged(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    fun onApellidoChanged(valor: String) {
        _estado.update { it.copy(apellido = valor, errores = it.errores.copy(apellido = null)) }
    }

    fun onEmailChanged(valor: String) {
        _estado.update { it.copy(email = valor, errores = it.errores.copy(email = null)) }
    }

    fun onContraseñaChanged(valor: String) {
        _estado.update { it.copy(contraseña = valor, errores = it.errores.copy(contraseña = null)) }
    }

    fun onConfirmarContraseñaChanged(valor: String) {
        _estado.update { it.copy(confirmarContraseña = valor, errores = it.errores) }
    }

    fun onAceptaTerminosChanged(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor, errores = it.errores.copy(aceptaTerminos = null)) }
    }

    private fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "El Nombre es requerido" else null,
            apellido = if (estadoActual.apellido.isBlank()) " El Apellido es requerido" else null,
            email = if (!estadoActual.email.contains("@")) "Correo inválido" else null,
            contraseña = if (estadoActual.contraseña.length < 6) "Contraseña inválida" else null,
            confirmarContraseña = if (estadoActual.confirmarContraseña != estadoActual.contraseña) "Las contraseñas no coinciden" else null,
            aceptaTerminos = if (!estadoActual.aceptaTerminos) "Debe aceptar los términos" else null
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.apellido,
            errores.email,
            errores.contraseña,
            errores.confirmarContraseña,
            errores.aceptaTerminos
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }
        return hayErrores
    }

    private fun validarLogin(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            email = if (estadoActual.email.isBlank()) "El email es requerido" else null,
            contraseña = if (estadoActual.contraseña.isBlank()) "La contraseña es requerida" else null
        )
        val hayErrores = listOfNotNull(errores.email, errores.contraseña).isNotEmpty()
        _estado.update { it.copy(errores = errores) }
        return hayErrores
    }
}
