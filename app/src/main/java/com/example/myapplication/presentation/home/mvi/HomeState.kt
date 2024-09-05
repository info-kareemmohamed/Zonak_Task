package com.example.myapplication.presentation.home.mvi

import com.example.myapplication.domain.model.Article

data class HomeState(
    val categories: List<String> = listOf(),
    val articlesItems: List<Article> = listOf(),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)