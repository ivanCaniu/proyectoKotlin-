package com.example.aplicacion.ui.theme.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aplicacion.ui.theme.componets.LogoutButton
import com.example.aplicacion.ui.theme.componets.MenuSection
import com.example.aplicacion.ui.theme.componets.ProfileHeader
import com.example.aplicacion.ui.theme.componets.StatsSection


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        // 1. Encabezado del perfil
        ProfileHeader(
            name = "Ivan", // Puedes obtener esto de un ViewModel
            email = "ivan.developer@email.com"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Sección de estadísticas
        StatsSection()

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Menú de opciones de la cuenta
        MenuSection()

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Botón para cerrar sesión
        LogoutButton(onLogout = onLogout)
    }
}