package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.details.DetailsScreen
import com.example.myapplication.presentation.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = navController, modifier = modifier)
        }

        composable(
            route = Route.DetailsScreen.route,
            arguments = Route.DetailsScreen.arguments
        ) {
            DetailsScreen(navController = navController, modifier = modifier)

        }

    }
}