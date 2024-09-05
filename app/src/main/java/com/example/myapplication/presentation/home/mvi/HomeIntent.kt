package com.example.myapplication.presentation.home.mvi


sealed class HomeIntent {
    data class SelectCategory(val category: String) : HomeIntent()
    object LoadCategories : HomeIntent()
    data class LoadArticles(val category: String) : HomeIntent()
}