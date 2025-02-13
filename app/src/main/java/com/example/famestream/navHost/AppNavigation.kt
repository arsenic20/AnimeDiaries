package com.example.famestream.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.famestream.model.Person
import com.example.famestream.screens.DetailScreen
import com.example.famestream.screens.PopularPersonsScreen
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            PopularPersonsScreen(
                onNavigateDetail = { article ->
                    val serializedPerson = Gson().toJson(article)
                    navController.navigate("details?person=$serializedPerson")
                }
            )
        }
        composable(
            route = "details?person={person}",
            arguments = listOf(navArgument("person") { type = NavType.StringType })
        ) { backStackEntry ->
            val serializedPerson = backStackEntry.arguments?.getString("person") ?: ""
            val person = Gson().fromJson(serializedPerson, Person::class.java)
            DetailScreen(person = person, onNavigateBack = { navController.popBackStack() })
        }

    }
}