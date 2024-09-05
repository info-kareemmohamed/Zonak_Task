package com.example.myapplication.presentation.details.mvi


sealed class DetailsIntent {
    object LoadArticleDetails : DetailsIntent()
    object ShareArticle : DetailsIntent()
    object OpenInBrowser : DetailsIntent()

}
