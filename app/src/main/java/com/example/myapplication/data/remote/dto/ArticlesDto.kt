package com.example.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class SourceDto(
    val id: String?,
    val name: String
)

data class ArticleDto(
    val source: SourceDto?,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    @field:Json(name = "urlToImage")
    val image: String,
    val publishedAt: String,
    val content: String
)

data class ArticlesDto (
    val articles: List<ArticleDto>
)