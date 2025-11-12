package com.example.aplicacion.data

import com.example.aplicacion.R
import com.example.aplicacion.model.Ejercicio

val listaDeEjercicios = listOf(
    Ejercicio(
        id = "flexiones",
        name = "Flexiones de Pecho",
        description = "Fortalece pecho, hombros y tríceps.",
        muscleGroup = "Torso",
        imageRes = R.drawable.ic_launcher_foreground, // Imagen de ejemplo
        detailedDescription = "Comienza en posición de plancha, con las manos un poco más anchas que los hombros. Baja el cuerpo hasta que el pecho casi toque el suelo. Mantén el core apretado y el cuerpo recto. Empuja hacia arriba hasta la posición inicial."
    ),
    Ejercicio(
        id = "sentadillas",
        name = "Sentadillas con Peso",
        description = "Excelente para piernas y glúteos.",
        muscleGroup = "Piernas",
        imageRes = R.drawable.ic_launcher_foreground, // Imagen de ejemplo
        detailedDescription = "Sostén una pesa rusa o mancuerna frente al pecho. Mantén la espalda recta y baja las caderas como si te fueras a sentar en una silla. Baja hasta que tus muslos estén paralelos al suelo y luego vuelve a subir."
    ),
    Ejercicio(
        id = "plancha",
        name = "Plancha Abdominal",
        description = "Fortalece el core y la espalda baja.",
        muscleGroup = "Abdomen",
        imageRes = R.drawable.ic_launcher_foreground, // Imagen de ejemplo
        detailedDescription = "Apoya los antebrazos en el suelo, con los codos alineados debajo de los hombros. Mantén el cuerpo en línea recta desde la cabeza hasta los talones. Sostén la posición, contrayendo los abdominales y los glúteos."
    ),
    Ejercicio(
        id = "dominadas",
        name = "Dominadas",
        description = "Trabaja la espalda y los bíceps.",
        muscleGroup = "Espalda",
        imageRes = R.drawable.ic_launcher_foreground, // Imagen de ejemplo
        detailedDescription = "Agarra una barra con las palmas mirando hacia adelante, un poco más anchas que los hombros. Cuelga con los brazos completamente extendidos. Tira de tu cuerpo hacia arriba hasta que la barbilla pase la barra. Baja de forma controlada."
    ),
    Ejercicio(
        id = "press_militar",
        name = "Press Militar",
        description = "Desarrolla la fuerza de los hombros.",
        muscleGroup = "Hombros",
        imageRes = R.drawable.ic_launcher_foreground, // Imagen de ejemplo
        detailedDescription = "De pie, sostén una barra o un par de mancuernas a la altura de los hombros, con las palmas hacia adelante. Empuja el peso hacia arriba hasta que los brazos estén completamente extendidos. Baja el peso de forma controlada a la posición inicial."
    )
)


fun getEjercicioById(id: String): Ejercicio? {
    return listaDeEjercicios.find { it.id == id }
}
