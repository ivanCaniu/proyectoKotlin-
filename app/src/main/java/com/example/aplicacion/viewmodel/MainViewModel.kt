package com.example.aplicacion.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.navegation.AppScreens
import com.example.aplicacion.navegation.NavigationEvent
import com.example.aplicacion.navegation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val name: String = "Ivan",
    val email: String = "ivan.developer@email.com",
    val bio: String = "Apasionado por el desarrollo de apps con Jetpack Compose.",
    val imageUri: Uri? = null
)

class MainViewModel: ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    fun updateUserProfile(name: String, email: String, bio: String) {
        _userProfile.value = _userProfile.value.copy(
            name = name,
            email = email,
            bio = bio
        )
    }

    fun updateProfileImage(uri: Uri?) {
        _userProfile.value = _userProfile.value.copy(imageUri = uri)
    }

    private val _navigationsEvents = MutableSharedFlow<NavigationEvent>()

    val navigationsEvents : SharedFlow<NavigationEvent> = _navigationsEvents.asSharedFlow()

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

    fun navigateBack(){
        CoroutineScope(Dispatchers.Main).launch {
            _navigationsEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    fun navigateUp(){
        CoroutineScope(Dispatchers.Main).launch {
            _navigationsEvents.emit(NavigationEvent.NavigateUp)
        }
    }
}