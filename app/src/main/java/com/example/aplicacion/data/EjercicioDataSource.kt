// En: app/src/main/java/com/example/aplicacion/data/EjercicioDataSource.kt
package com.example.aplicacion.data
import com.example.aplicacion.model.Ejercicio
import com.example.aplicacion.model.Rutina
import com.example.aplicacion.data.rutina.ejerciciosDeTorso
import com.example.aplicacion.data.rutina.ejerciciosDePiernas



val listaDeRutinas = listOf(
    Rutina(nombre = "Rutina de Torso", ejercicios = ejerciciosDeTorso),
    Rutina(nombre = "Rutina de Piernas", ejercicios = ejerciciosDePiernas),
)

fun getEjercicioById(id: String): Ejercicio? {
    return listaDeRutinas.flatMap { it.ejercicios }.find { it.id == id }
}




