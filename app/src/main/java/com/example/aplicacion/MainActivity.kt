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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.aplicacion.navegation.AppScreens
import com.example.aplicacion.navegation.NavigationEvent
import com.example.aplicacion.ui.theme.AplicacionTheme
import com.example.aplicacion.ui.theme.componets.BottomNavigationBar
import com.example.aplicacion.ui.theme.screen.AyudaSoporteScreen
import com.example.aplicacion.ui.theme.screen.EditarPerfilScreen
import com.example.aplicacion.ui.theme.screen.EjercicioDetailScreen
import com.example.aplicacion.ui.theme.screen.HomeScreen
import com.example.aplicacion.ui.theme.screen.LoginScreen
import com.example.aplicacion.ui.theme.screen.MealDetailScreen
import com.example.aplicacion.ui.theme.screen.MealListScreen
import com.example.aplicacion.ui.theme.screen.NotificacionesScreen
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
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        val showBottomBar = currentDestination?.hierarchy?.any { it.route?.startsWith(AppScreens.Home.route) == true || it.route?.startsWith(AppScreens.Foods.route) == true || it.route?.startsWith(AppScreens.Profile.route) == true } == true

                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = AppScreens.Home.route,
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    ) {
                        composable(route = AppScreens.Home.route) {
                            HomeScreen(viewModel = viewModel, navController = navController)
                        }

                        composable(route = AppScreens.Foods.route) {
                            MealListScreen(
                                onMealClick = { mealId ->
                                    navController.navigate("meal_detail/$mealId")
                                }
                            )
                        }

                        composable(

                            route = "meal_detail/{mealId}",
                            arguments = listOf(navArgument("mealId") { type = NavType.StringType }),

                            enterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            exitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                            },
                            popEnterTransition = {
                                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            },
                            popExitTransition = {
                                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                            }
                        ) { backStackEntry ->

                            val mealId = backStackEntry.arguments?.getString("mealId")

                            // Es una buena práctica asegurarse de que el ID no es nulo
                            requireNotNull(mealId) { "El ID de la comida no puede ser nulo" }

                            // Llamamos a la pantalla de detalle que ya creaste
                            MealDetailScreen(
                                mealId = mealId,
                                onNavigateBack = {
                                    navController.popBackStack() // Acción para volver a la pantalla anterior
                                }
                            )
                        }

                        composable(
                            route = AppScreens.Profile.route + "?userId={userId}",
                            arguments = listOf(
                                navArgument("userId") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId") ?: ""
                            ProfileScreen(viewModel = viewModel, userId = userId, navController = navController)
                        }

                        composable(route = "registro"){
                            RegistroScreen(navController = navController, viewModel = viewModel<UsuarioViewModel>())
                        }

                        composable(route = "login"){
                            LoginScreen(navController = navController, viewModel = viewModel<UsuarioViewModel>())
                        }

                        composable("editar_perfil") { EditarPerfilScreen(navController = navController, viewModel = viewModel) }
                        composable("notificaciones") { NotificacionesScreen(navController) }
                        composable("ayuda_soporte") { AyudaSoporteScreen(navController) }

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