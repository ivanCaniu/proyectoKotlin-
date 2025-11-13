package com.example.aplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.aplicacion.navegation.NavigationEvent
import com.example.aplicacion.navegation.Screen
import com.example.aplicacion.ui.theme.AplicacionTheme
import com.example.aplicacion.ui.theme.screen.EjercicioDetailScreen
import com.example.aplicacion.ui.theme.screen.HomeScreen
import com.example.aplicacion.ui.theme.screen.LoginScreen
import com.example.aplicacion.ui.theme.screen.ProfileScreen
import com.example.aplicacion.ui.theme.screen.RegistroScreen
import com.example.aplicacion.viewmodel.MainViewModel
import com.example.aplicacion.viewmodel.UsuarioViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicacionTheme {
                val viewModel: MainViewModel = viewModel()
                val navController = rememberAnimatedNavController()

                LaunchedEffect(key1 = Unit) {
                    viewModel.navigationsEvents.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(route = event.route) {
                                    event.popUpTo?.let {
                                        popUpTo(it) {
                                            inclusive = event.inclusive
                                        }
                                    }
                                    launchSingleTop = event.singleTop
                                    restoreState = true
                                }
                            }
                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    ) {
                        composable(route = Screen.Home.route) {
                            HomeScreen(viewModel = viewModel, navController = navController)
                        }

                        composable(
                            route = Screen.Profile.route,
                            arguments = listOf(navArgument("userId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId") ?: ""
                            ProfileScreen(viewModel = viewModel, userId = userId)
                        }

                        composable(route = Screen.Registro.route){
                            RegistroScreen(navController = navController, viewModel = viewModel<UsuarioViewModel>())
                        }

                        composable(route = Screen.Login.route){
                            LoginScreen(navController = navController, viewModel = viewModel<UsuarioViewModel>())
                        }

                        composable(
                            route = "ejercicio_detail/{ejercicioId}",
                            arguments = listOf(navArgument("ejercicioId") { type = NavType.StringType }),
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            }
                        ) { backStackEntry ->
                            val ejercicioId = backStackEntry.arguments?.getString("ejercicioId")
                            if (ejercicioId != null) {
                                EjercicioDetailScreen(ejercicioId = ejercicioId, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
