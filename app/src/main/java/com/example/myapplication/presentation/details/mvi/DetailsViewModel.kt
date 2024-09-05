package com.example.myapplication.presentation.details.mvi

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.myapplication.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(applicationContext) {
    private val _state = mutableStateOf<DetailsState>(DetailsState.Loading)
    val state: State<DetailsState> = _state

    init {
        loadArticleDetails()
    }

    fun processIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadArticleDetails -> loadArticleDetails()
            is DetailsIntent.ShareArticle -> shareArticle(applicationContext.applicationContext)
            is DetailsIntent.OpenInBrowser -> openInBrowser(applicationContext.applicationContext)
        }
    }

    private fun loadArticleDetails() {
        val args = savedStateHandle.get<String>("article")
        if (args != null) {
            val decodedArticleJson = Uri.decode(args)
            val article = Json.decodeFromString<Article>(decodedArticleJson)
            _state.value = DetailsState.ArticleDetailsLoaded(article)
        } else
            _state.value = DetailsState.Error("Invalid article")
    }

    private fun shareArticle(context: Context) {
        val currentState = _state.value
        if (currentState is DetailsState.ArticleDetailsLoaded) {
            Intent(Intent.ACTION_SEND).also {
                it.putExtra(Intent.EXTRA_TEXT, currentState.article.url)
                it.type = "text/plain"
                if (it.resolveActivity(context.packageManager) != null) {
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(it)
                }
            }
        }
    }

    private fun openInBrowser(context: Context) {
        val currentState = _state.value
        if (currentState is DetailsState.ArticleDetailsLoaded) {
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(currentState.article.url)
                if (it.resolveActivity(context.packageManager) != null) {
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(it)
                }
            }
        }
    }
}