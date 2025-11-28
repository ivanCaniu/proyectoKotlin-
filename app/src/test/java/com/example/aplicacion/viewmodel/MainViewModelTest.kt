package com.example.aplicacion.viewmodel

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun updateUserProfile() = runTest  (testDispatcher){
        val nuevoNombre = "Ivan Pro"
        val nuevoEmail = "ivan.pro@test.com"
        val nuevaBio = "Desarrollador Android experto."


        viewModel.updateUserProfile(nuevoNombre, nuevoEmail, nuevaBio)

        val estadoActual = viewModel.userProfile.value
        assertEquals(nuevoNombre, estadoActual.name)
        assertEquals(nuevoEmail, estadoActual.email)
        assertEquals(nuevaBio, estadoActual.bio)
    }


    @Test
    fun updateUserProfileVacio() = runTest  (testDispatcher){
        val nuevoNombre = ""
        val nuevoEmail = ""
        val nuevaBio = ""


        viewModel.updateUserProfile(nuevoNombre,nuevoEmail,nuevaBio)

        val estadoActual = viewModel.userProfile.value
        assertEquals("el nombre no puede estar vacio",nuevoNombre,estadoActual.name)
        assertEquals("el email no puede estar vacio",nuevoEmail,estadoActual.email)
        assertEquals("la bio no puede estar vacia",nuevaBio,estadoActual.bio)
    }


    @Test
    fun viewModelTest() {

        val estadoInicial = viewModel.userProfile.value


        assertEquals("Ivan", estadoInicial.name)
        assertEquals("ivan.developer@email.com", estadoInicial.email)
        assertEquals("Apasionado por el desarrollo ", estadoInicial.bio)
    }



}