package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Article
import com.example.myapplication.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getArticles(category: String): Flow<Resource<List<Article>>>
    fun getCategories(): List<String>
}