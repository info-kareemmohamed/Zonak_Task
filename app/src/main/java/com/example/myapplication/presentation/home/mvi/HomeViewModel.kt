package com.example.myapplication.presentation.home.mvi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.ArticleSharedPreferences
import com.example.myapplication.core.isInternetAvailable
import com.example.myapplication.domain.usecase.CheckInternetUseCase
import com.example.myapplication.domain.usecase.GetArticlesUseCase
import com.example.myapplication.domain.usecase.GetCategoriesUseCase
import com.example.myapplication.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    applicationContext: Application,
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    checkInternetUseCase: CheckInternetUseCase,
    articleSharedPreferences: ArticleSharedPreferences
) : AndroidViewModel(application = applicationContext) {
    private val _state = MutableStateFlow(HomeState(isLoading = true))
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private var _internetConnection = false

    init {

            observeInternetConnection(checkInternetUseCase)
            val categoryName = articleSharedPreferences.getCategoryName()
            loadInitialData(categoryName, isInternetAvailable(getApplication()))

        }

    private fun loadInitialData(categoryNameFromShared: String, checkInternet: Boolean) {
        processIntent(HomeIntent.LoadCategories)
        if (state.value.categories.isNotEmpty()) {
            val selectedCategory =
                if (!checkInternet && categoryNameFromShared.isNotEmpty()) {
                    categoryNameFromShared

                } else {
                    state.value.categories.firstOrNull() ?: ""
                }
            processIntent(HomeIntent.SelectCategory(selectedCategory))
        }
    }

    fun processIntent(intent: HomeIntent) {
        viewModelScope.launch {
            when (intent) {
                is HomeIntent.LoadCategories -> loadCategories()
                is HomeIntent.LoadArticles -> loadArticles(intent.category)
                is HomeIntent.SelectCategory -> selectCategory(intent.category)
            }
        }
    }

    private fun selectCategory(category: String) {
        _state.value = _state.value.copy(selectedCategory = category)
        processIntent(HomeIntent.LoadArticles(category))
    }

    private fun loadCategories() {
        val categories = getCategoriesUseCase()
        _state.value = _state.value.copy(categories = categories)
    }

    private suspend fun loadArticles(category: String) {
        _state.value = _state.value.copy(isLoading = true)
        getArticlesUseCase(category).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        articlesItems = result.data ?: emptyList(),
                        isLoading = false,
                        errorMessage = null
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = result.message ?: "Unknown error"
                    )

                }
            }
        }
    }

    private fun observeInternetConnection(
        checkInternetUseCase: CheckInternetUseCase
    ) {
        viewModelScope.launch {
            checkInternetUseCase(getApplication()).collect { isConnected ->
                if (isConnected) {
                    if (!_internetConnection && !state.value.selectedCategory.isNullOrBlank()) {
                        processIntent(HomeIntent.LoadArticles(state.value.selectedCategory!!))
                    }
                }
                _internetConnection = isConnected
            }
        }
    }
}
