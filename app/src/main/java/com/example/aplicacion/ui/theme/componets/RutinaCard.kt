
package com.example.aplicacion.ui.theme.componets
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacion.model.Rutina

@Composable
fun RutinaCard(
    rutina: Rutina,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Título de la sección de la rutina
        Text(
            text = rutina.nombre,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Fila horizontal de ejercicios que se puede desplazar
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = rutina.ejercicios,
                key = { it.id }
            ) { ejercicio ->
                // Reutilizamos el EjercicioCard que ya tenías, pero le damos un tamaño fijo
                EjercicioCard(
                    ejercicio = ejercicio,
                    navController = navController,
                    modifier = Modifier.width(200.dp) // Ancho fijo para que quepan varias en pantalla
                )
            }
        }
    }
}


