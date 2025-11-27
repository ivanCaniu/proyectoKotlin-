package com.example.aplicacion.ui.theme.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants


@Composable
fun StatsSection() {
    Text(
        text = "Mis Estadísticas",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        StatCard(icon = Icons.Default.CalendarMonth, label = "Días Activo", value = "125")
        StatCard(icon = Icons.Default.MonitorWeight, label = "Peso Actual", value = "75 kg")
        StatCard(icon = Icons.Default.BarChart, label = "Progreso", value = "+5%")
    }
}

@Composable
fun StatCard(icon: JdkConstants.CalendarMonth, label: String, value: String) {
    TODO("Not yet implemented")
}