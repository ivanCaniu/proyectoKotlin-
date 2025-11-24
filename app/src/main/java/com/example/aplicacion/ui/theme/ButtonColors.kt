package com.example.aplicacion.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable


@Composable
fun primaryButtonColors(): ButtonColors = ButtonDefaults.buttonColors(

    containerColor = Blue_Button_Normal, // Nuestro azul personalizado


    contentColor = White_Text_Button,


    disabledContainerColor = Gray_Button_Disabled,


    disabledContentColor = Gray_Text_Disabled
)
