package com.example.myapplication.presentation.home

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.common.component.ErrorScreen
import com.example.myapplication.presentation.common.component.LoadingScreen
import com.example.myapplication.presentation.home.component.ArticlesList
import com.example.myapplication.presentation.home.component.FilterChipGroup
import com.example.myapplication.presentation.home.mvi.HomeIntent
import com.example.myapplication.presentation.home.mvi.HomeViewModel
import com.example.myapplication.presentation.navigation.Route
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()


    Column(modifier = modifier) {
        FilterChipGroup(
            chips = state.categories,
            selectedChip = state.selectedCategory,
            onChipSelected = { category ->
                if (category != state.selectedCategory)
                viewModel.processIntent(HomeIntent.SelectCategory(category))
            }
        )
        Spacer(modifier = Modifier.height(7.dp))

        when {
            !state.errorMessage.isNullOrBlank() -> ErrorScreen(message = state.errorMessage.toString())
            state.isLoading -> LoadingScreen()
            else -> ArticlesList(articles = state.articlesItems) { article ->
                val articleJson = Uri.encode(Json.encodeToString(article))
                navController.navigate(
                    Route.DetailsScreen.route.replace("{article}", articleJson)
                )
            }
        }
    }
}








