package com.example.aplicacion.model

import android.net.Uri

data class UserProfile(
    val name: String = "Ivan",
    val email: String = "ivan.developer@email.com",
    val bio: String = "Apasionado por el desarrollo ",
    val imageUri: Uri? = null
)


data class EditProfileFormState(
    val name: String = "",
    val email: String = "",
    val bio: String = "",
    val nameError: String? = null, 
    val emailError: String? = null,
    val isSaveEnabled: Boolean = false
)