package com.example.aplicacion.ui.theme.componets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aplicacion.navegation.Screen
import com.example.aplicacion.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    viewModel: MainViewModel,
    currentRoute: String,
    content: @Composable (openDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))

                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = currentRoute == Screen.Home.route,
                    onClick = {
                        scope.launch { drawerState.close() }
                        if (currentRoute != Screen.Home.route) {
                            viewModel.navigateTo(Screen.Home)
                        }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    selected = currentRoute.contains(Screen.Profile.route),
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Profile.createRoute("123"))
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Cerrar Sesión") },
                    selected = currentRoute == Screen.Registro.route,
                    onClick = {
                        scope.launch { drawerState.close() }
                        if (currentRoute != Screen.Login.route) {
                            viewModel.navigateTo(Screen.Registro)
                        }
                    }
                )
            }
        }
    ) {
        content {
            scope.launch { drawerState.open() }
        }
    }
}