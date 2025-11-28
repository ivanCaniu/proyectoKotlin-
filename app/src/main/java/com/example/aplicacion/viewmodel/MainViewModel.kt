package com.example.aplicacion.viewmodel

import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.model.EditProfileFormState
import com.example.aplicacion.model.UserProfile
import com.example.aplicacion.navegation.AppScreens
import com.example.aplicacion.navegation.NavigationEvent
import com.example.aplicacion.navegation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _editFormState = MutableStateFlow(EditProfileFormState())
    val editFormState: StateFlow<EditProfileFormState> = _editFormState.asStateFlow()

    init {
        _editFormState.update {
            it.copy(
                name = _userProfile.value.name,
                email = _userProfile.value.email,
                bio = _userProfile.value.bio
            )
        }
    }

    fun onProfileFormFieldChange(name: String, email: String, bio: String) {
        val nameError = if (name.isBlank()) "El nombre no puede estar vacío" else null
        val emailError = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "El correo no es válido" else null

        _editFormState.update {
            it.copy(
                name = name,
                email = email,
                bio = bio,
                nameError = nameError,
                emailError = emailError,
                isSaveEnabled = nameError == null && emailError == null
            )
        }
    }

    fun updateUserProfile() {
        if (_editFormState.value.isSaveEnabled) {
            _userProfile.update {
                it.copy(
                    name = _editFormState.value.name,
                    email = _editFormState.value.email,
                    bio = _editFormState.value.bio
                )
            }
        }
    }

    // --- Nueva Función ---
    fun saveChangesAndNavigateBack() {
        if (_editFormState.value.isSaveEnabled) {
            updateUserProfile()
            navigateBack()
        }
    }

    fun updateProfileImage(uri: Uri?) {
        _userProfile.update { it.copy(imageUri = uri) }
    }

    private val _navigationsEvents = MutableSharedFlow<NavigationEvent>()
    val navigationsEvents: SharedFlow<NavigationEvent> = _navigationsEvents.asSharedFlow()

    fun navigateTo(route: String, popUpTo: String? = null, inclusive: Boolean = false, singleTop: Boolean = false) {
        viewModelScope.launch {
            _navigationsEvents.emit(
                NavigationEvent.NavigateTo(
                    route = route,
                    popUpTo = popUpTo,
                    inclusive = inclusive,
                    singleTop = singleTop
                )
            )
        }
    }

    fun navigateTo(screen: Screen, popUpTo: Screen? = null, inclusive: Boolean = false, singleTop: Boolean = false) {
        navigateTo(screen.route, popUpTo?.route, inclusive, singleTop)
    }

    fun logout() {
        navigateTo(
            route = "login",
            popUpTo = AppScreens.Home.route,
            inclusive = true
        )
    }

    fun onLogoutClick() {
        navigateTo(
            route = Screen.Login.route,
            popUpTo = Screen.Home.route,
            inclusive = true
        )
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _navigationsEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    fun navigateUp() {
        viewModelScope.launch {
            _navigationsEvents.emit(NavigationEvent.NavigateUp)
        }
    }
}