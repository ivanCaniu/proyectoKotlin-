package com.example.aplicacion.data.rutina

import com.example.aplicacion.R
import com.example.aplicacion.model.Ejercicio

internal val ejerciciosDeTorso = listOf(
    Ejercicio(
        id = "flexiones",
        name = "Flexiones",
        description = "Pecho, hombros, tríceps",
        muscleGroup = "Torso",
        imageRes = R.drawable.flexione,
        detailedDescription = "Apoya las manos al ancho de los hombros. Mantén el cuerpo recto como una tabla. Baja de forma controlada hasta que el pecho casi toque el suelo. Sube extendiendo los brazos. Series: 3, Reps: 8-12"
    ),
    Ejercicio(
        id = "remo_con_mochila",
        name = "Remo con Mochila",
        description = "Espalda y bíceps",
        muscleGroup = "Torso",
        imageRes = R.drawable.remo,
        detailedDescription = "Inclina el torso 45°. Toma la mochila por las asas. Tira hacia tu abdomen, juntando los omóplatos. Baja lentamente. Series: 3, Reps: 10-12"
    ),
    Ejercicio(
        id = "fondos_en_sillas",
        name = "Fondos en Sillas",
        description = "Tríceps, pecho bajo",
        muscleGroup = "Torso",
        imageRes = R.drawable.fondo,
        detailedDescription = "Coloca dos sillas firmes. Apoya las manos detrás del cuerpo. Baja doblando los codos hacia atrás. Sube extendiendo los brazos. Series: 3, Reps: 8-12"
    ),
    Ejercicio(
        id = "elevaciones_laterales",
        name = "Elevaciones Laterales",
        description = "Hombro lateral",
        muscleGroup = "Torso",
        imageRes = R.drawable.laterales,
        detailedDescription = "Con un peso ligero en cada mano, brazos a los lados. Levanta los brazos hasta alinearlos con los hombros. Baja lentamente. Series: 3, Reps: 12-15"
    ),
    Ejercicio(
        id = "curl_de_biceps",
        name = "Curl de Bíceps",
        description = "Bíceps",
        muscleGroup = "Torso",
        imageRes = R.drawable.biceps,
        detailedDescription = "Mantén los codos pegados al cuerpo. Sube el peso de forma controlada y aprieta en la parte alta. Baja lentamente. Series: 3, Reps: 10-12"
    ),
    Ejercicio(
        id = "flexiones_diamante",
        name = "Flexiones Diamante",
        description = "Tríceps, pecho interior",
        muscleGroup = "Torso",
        imageRes = R.drawable.flexione, // Reemplazar con una imagen adecuada si la tienes
        detailedDescription = "Junta las manos en el suelo formando un diamante con los dedos. Realiza una flexión manteniendo los codos cerca del cuerpo. Series: 3, Reps: 8-10"
    ),
    Ejercicio(
        id = "dominadas",
        name = "Dominadas",
        description = "Espalda, bíceps",
        muscleGroup = "Torso",
        imageRes = R.drawable.remo, // Reemplazar con una imagen adecuada si la tienes
        detailedDescription = "Sujeta una barra con las manos un poco más anchas que los hombros. Tira de tu cuerpo hacia arriba hasta que la barbilla pase la barra. Baja de forma controlada. Series: 3, Reps: al fallo"
    )
)