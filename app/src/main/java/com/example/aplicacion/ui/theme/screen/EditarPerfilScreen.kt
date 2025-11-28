package com.example.aplicacion.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacion.ui.theme.componets.ProfileImagePicker
import com.example.aplicacion.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val formState by viewModel.editFormState.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImagePicker(
                imageUri = userProfile.imageUri,
                onImageUriChanged = { newUri -> viewModel.updateProfileImage(newUri) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = formState.name,
                onValueChange = { newName ->
                    viewModel.onProfileFormFieldChange(newName, formState.email, formState.bio)
                },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = formState.nameError != null,
                supportingText = {
                    if (formState.nameError != null) {
                        Text(text = formState.nameError!!)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.email,
                onValueChange = { newEmail ->
                    viewModel.onProfileFormFieldChange(formState.name, newEmail, formState.bio)
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = formState.emailError != null,
                supportingText = {
                    if (formState.emailError != null) {
                        Text(text = formState.emailError!!)
                    }
                }
            )

             Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.bio,
                onValueChange = { newBio ->
                    viewModel.onProfileFormFieldChange(formState.name, formState.email, newBio)
                },
                label = { Text("Biografía") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.saveChangesAndNavigateBack() },
                enabled = formState.isSaveEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}