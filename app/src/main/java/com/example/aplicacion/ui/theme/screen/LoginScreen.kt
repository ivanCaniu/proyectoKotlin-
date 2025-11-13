package com.example.aplicacion.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun LoginScreen(
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // Campo de Email
        OutlinedTextField(
            value = estado.email,
            onValueChange = { viewModel.onEmailChanged(it) },
            label = { Text("Email") },
            isError = estado.errores.email != null,
            supportingText = {
                estado.errores.email?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de Contraseña
        OutlinedTextField(
            value = estado.contraseña,
            onValueChange = { viewModel.onContraseñaChanged(it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = estado.errores.contraseña != null,
            supportingText = {
                estado.errores.contraseña?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))


        PrimaryButton(
            text = "Iniciar Sesión",
            onClick = { viewModel.onLoginClick() },
            enabled = !estado.isLoading,
            isLoading = estado.isLoading
        )

        TextButton(
            onClick = { navController.navigate("registro") },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = !estado.isLoading
        ) {
            Text("¿No tienes una cuenta? Regístrate")
        }
    }
}
