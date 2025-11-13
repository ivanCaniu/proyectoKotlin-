package com.example.aplicacion.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacion.ui.theme.componets.PrimaryButton
import com.example.aplicacion.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(
    navController: NavController,
    viewModel: UsuarioViewModel
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationEvent.collect { route ->
            navController.navigate(route)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedTextField(
            value = estado.nombre,
            onValueChange = { viewModel.onNombreChanged(it) },
            label = { Text(text = "Nombre") },
            isError = estado.errores.nombre != null,
            supportingText = {
                estado.errores.nombre?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.apellido,
            onValueChange = { viewModel.onApellidoChanged(it) },
            label = { Text(text = "Apellido") },
            isError = estado.errores.apellido != null,
            supportingText = {
                estado.errores.apellido?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.email,
            onValueChange = { viewModel.onEmailChanged(it) },
            label = { Text(text = "Email") },
            isError = estado.errores.email != null,
            supportingText = {
                estado.errores.email?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.contraseña,
            onValueChange = { viewModel.onContraseñaChanged(it) },
            label = { Text(text = "Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = estado.errores.contraseña != null,
            supportingText = {
                estado.errores.contraseña?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.confirmarContraseña,
            onValueChange = { viewModel.onConfirmarContraseñaChanged(it) },
            label = { Text(text = "Confirmar Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = estado.errores.confirmarContraseña != null,
            supportingText = {
                estado.errores.confirmarContraseña?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        estado.errores.aceptaTerminos?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = estado.aceptaTerminos,
                onCheckedChange = { viewModel.onAceptaTerminosChanged(it) }
            )
            Spacer(Modifier.width(width = 8.dp))
            Text(text = "Acepto los términos y condiciones")
        }



        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = "Registrarse",
            onClick = { viewModel.onRegistroClick() },
            enabled = !estado.isLoading,
            isLoading = estado.isLoading
        )


        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = !estado.isLoading
        ) {
            Text("¿Ya tienes una cuenta? Inicia sesión")
        }
    }
}