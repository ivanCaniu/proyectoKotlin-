package com.example.aplicacion.data.rutina

import com.example.aplicacion.R
import com.example.aplicacion.model.Ejercicio

internal val ejerciciosDeTorso = listOf(
    Ejercicio(
        id = "flexiones",
        name = "Flexiones",
        description = "Pecho,hombros,triceps",
        muscleGroup = "Torso",
        imageRes = R.drawable.flexione,
        detailedDescription = "Apoya manos al ancho de los hombros.\n" +
                "Mantén el cuerpo recto como tabla.\n" +
                "Baja controlado hasta que el pecho casi toque el piso.\n" +
                "Sube extendiendo brazos.\n" +
                "Series de 3, con Reps: 8-12"
    ),
    Ejercicio(
        id = "Remo con mochila",
        name = "Remo con Mochila",
        description = "espalda y biceps.",
        muscleGroup = "Torso",
        imageRes = R.drawable.remo,
        detailedDescription = " Inclina el torso 45° \n"+
                "Toma la mochila por las asas. \n"+
                "Tira hacia tu abdomen. \n"+
                "Baja lento \n"+
                "Series de 3, con Reps: 10-12"
    ),

    Ejercicio(
        id = "Fondos entre sillas",
        name = "Fondos con Sillas",
        description = "triceps, pecho bajo",
        muscleGroup = "Torso",
        imageRes = R.drawable.fondo,
        detailedDescription = "Coloca dos sillas firmes \n"+
                "Apoya manos atras del cuerpo\n"+
                "Baja doblando codos hacia atras\n"+
                "Sube extendido.\n"+
                "Series de 3, con Reps: 8-12"
    ),

    Ejercicio(
        id = "Elevaciones Laterales",
        name = "Elevaciones Laterales",
        description = "hombro lateral",
        muscleGroup = "Torso",
        imageRes = R.drawable.laterales,
        detailedDescription = "Brazos a los lados.\n"+
                "Levanta hasta alinearlos con los hombros.\n"+
                "Baja lentamente.\n"+
                "Series de 3, con Reps: 12-15"
    ),

    Ejercicio(
        id = "Curl de biceps",
        name = "Curl de biceps",
        description = "biceps",
        muscleGroup = "Torso",
        imageRes = R.drawable.biceps,
        detailedDescription = "Manten codos pegados al cuerpo.\n"+
                "Sube lento aprieta arriba.\n"+
                "Baja controlado.\n"+
                "Series de 3, con Reps: 10-12"
    )
)