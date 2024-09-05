package com.example.myapplication.data.mappers

import com.example.myapplication.data.remote.dto.ArticleDto
import com.example.myapplication.domain.model.Article


fun ArticleDto.toArticle(category: String): Article {
    return Article(
        source = source?.name ?: "",
        author = author ?: "",
        title = title?:"" ,
        description = description?:"" ,
        url = url?:"",
        image = image?:"" ,
        publishedAt = publishedAt?:"" ,
        content = content ?:"",
        category = category
    )
}