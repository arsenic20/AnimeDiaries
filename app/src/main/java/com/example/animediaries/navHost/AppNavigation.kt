package com.example.animediaries.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animediaries.screens.AnimeDetailScreen
import com.example.animediaries.screens.PopularAnimeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            PopularAnimeScreen(
                onNavigateDetail = { animeId ->
                    navController.navigate("anime_detail/animeId=$animeId")
                }
            )
        }
        composable(
            route = "anime_detail/animeId={animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val animeId = backStackEntry.arguments?.getInt("animeId") ?: return@composable
                AnimeDetailScreen(
                    animeId = animeId,
                    onBackPressed = { navController.popBackStack() }
                )
            }
    }
}