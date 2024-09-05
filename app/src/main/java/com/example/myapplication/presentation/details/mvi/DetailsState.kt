package com.example.myapplication.presentation.details.mvi

import com.example.myapplication.domain.model.Article

sealed class DetailsState {
    object Loading : DetailsState()
    data class ArticleDetailsLoaded(val article: Article) : DetailsState()
    data class Error(val message: String) : DetailsState()
}