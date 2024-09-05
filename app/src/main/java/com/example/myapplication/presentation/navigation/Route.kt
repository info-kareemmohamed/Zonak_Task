package com.example.myapplication.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")

    object DetailsScreen :
        Route(route = "detailsScreen/{article}", arguments = listOf(navArgument("article") {
            type = NavType.StringType
        }))

}