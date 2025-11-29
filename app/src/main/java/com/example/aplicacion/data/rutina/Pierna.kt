package com.example.aplicacion.data.rutina

import com.example.aplicacion.R
import com.example.aplicacion.model.Ejercicio

internal val ejerciciosDePiernas = listOf(
    Ejercicio(
        id = "sentadillas",
        name = "Sentadillas",
        description = "Cuádriceps, glúteos, isquiotibiales",
        muscleGroup = "Piernas",
        imageRes = R.drawable.ic_launcher_foreground,
        detailedDescription = "Mantén la espalda recta y baja como si te sentaras en una silla. Las rodillas no deben sobrepasar la punta de los pies. Series: 3, Reps: 10-15"
    ),
    Ejercicio(
        id = "zancadas",
        name = "Zancadas",
        description = "Cuádriceps, glúteos",
        muscleGroup = "Piernas",
        imageRes = R.drawable.ic_launcher_foreground, // Reemplazar con una imagen adecuada si la tienes
        detailedDescription = "Da un paso hacia adelante y baja la cadera hasta que ambas rodillas estén en un ángulo de 90 grados. Vuelve a la posición inicial y alterna. Series: 3, Reps: 10-12 por pierna"
    ),
    Ejercicio(
        id = "puente_de_gluteos",
        name = "Puente de Glúteos",
        description = "Glúteos, isquiotibiales",
        muscleGroup = "Piernas",
        imageRes = R.drawable.ic_launcher_foreground, // Reemplazar con una imagen adecuada si la tienes
        detailedDescription = "Acuéstate boca arriba con las rodillas dobladas. Levanta la cadera hacia el techo, apretando los glúteos. Mantén la posición y baja lentamente. Series: 3, Reps: 15-20"
    ),
    Ejercicio(
        id = "peso_muerto_rumano",
        name = "Peso Muerto Rumano",
        description = "Isquiotibiales, glúteos, espalda baja",
        muscleGroup = "Piernas",
        imageRes = R.drawable.ic_launcher_foreground, // Reemplazar con una imagen adecuada si la tienes
        detailedDescription = "Sostén un peso frente a ti. Con las piernas casi rectas, baja el torso manteniendo la espalda recta. Siente el estiramiento en los isquiotibiales. Sube de forma controlada. Series: 3, Reps: 10-12"
    ),
    Ejercicio(
        id = "elevacion_talones",
        name = "Elevaciones de Talones",
        description = "Gemelos",
        muscleGroup = "Piernas",
        imageRes = R.drawable.ic_launcher_foreground, // Reemplazar con una imagen adecuada si la tienes
        detailedDescription = "De pie, elévate sobre las puntas de los pies tan alto como puedas. Mantén la contracción y baja lentamente. Puedes añadir peso para más dificultad. Series: 3, Reps: 15-25"
    )
)
