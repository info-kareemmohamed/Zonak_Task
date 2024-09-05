package com.example.myapplication.presentation.details


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.presentation.details.component.DetailsContent
import com.example.myapplication.presentation.common.component.ErrorScreen
import com.example.myapplication.presentation.common.component.LoadingScreen
import com.example.myapplication.presentation.details.mvi.DetailsIntent
import com.example.myapplication.presentation.details.mvi.DetailsState
import com.example.myapplication.presentation.details.mvi.DetailsViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state = viewModel.state.value
    when (state) {
        is DetailsState.Loading -> LoadingScreen()
        is DetailsState.ArticleDetailsLoaded -> {
            DetailsContent(
                modifier = modifier,
                article = state.article,
                onBrowsingClick = {viewModel.processIntent(DetailsIntent.OpenInBrowser)},
                onShareClick = { viewModel.processIntent(DetailsIntent.ShareArticle) },
                onBackClick = { navController.popBackStack() }
            )
        }

        is DetailsState.Error -> ErrorScreen(message = state.message)
    }


}


