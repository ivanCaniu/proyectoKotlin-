package com.example.aplicacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion.navegation.NavigationEvent
import com.example.aplicacion.navegation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

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
