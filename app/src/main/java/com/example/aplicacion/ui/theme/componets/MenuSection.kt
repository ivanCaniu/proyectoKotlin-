package com.example.aplicacion.ui.theme.componets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MenuSection() {
    Text(
        text = "Ajustes de la Cuenta",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    ProfileMenuItem(icon = Icons.Default.Edit, text = "Editar Perfil") { /* Acción al hacer clic */ }
    ProfileMenuItem(icon = Icons.Default.NotificationsNone, text = "Notificaciones") { /* Acción al hacer clic */ }
    ProfileMenuItem(icon = Icons.Default.HelpOutline, text = "Ayuda y Soporte") { /* Acción al hacer clic */ }
}