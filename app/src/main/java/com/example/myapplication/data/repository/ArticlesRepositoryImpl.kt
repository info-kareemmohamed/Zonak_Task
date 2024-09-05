package com.example.myapplication.data.repository

import com.example.myapplication.core.ArticleSharedPreferences
import com.example.myapplication.data.local.ArticleDao
import com.example.myapplication.data.mappers.toArticle
import com.example.myapplication.data.remote.ArticlesApiService
import com.example.myapplication.domain.model.Article
import com.example.myapplication.domain.repository.ArticlesRepository
import com.example.myapplication.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * ArticlesRepositoryImpl is an implementation of the ArticlesRepository interface.
 *
 * This class adheres to the Single Source of Truth (SSOT) principle by ensuring
 * that data is synchronized between remote and local sources, providing a single
 * source of truth for article data.
 *
 * - Fetches articles from a remote API and saves them in a local database.
 * - Clears existing articles in the database for the specified category before saving new ones.
 * - Retrieves articles from the local database to ensure consistency and availability.
 * - Provides a unified data source for the rest of the application, ensuring that
 *   any component using this repository always interacts with a consistent view of the data.
 */

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesApiService: ArticlesApiService,
    private val articleDao: ArticleDao,
    private val articleSharedPreferences: ArticleSharedPreferences

) : ArticlesRepository {
    override suspend fun getArticles(category: String): Flow<Resource<List<Article>>> = flow {

        try {
            // Fetch remote articles
            val remoteArticles = articlesApiService.getArticles(category).articles.map { it.toArticle(category) }

            // Save them in the database (clear existing ones first) because i want to save only last category
            articleDao.deleteAllArticles()
            articleDao.insertArticles(remoteArticles)

            // Emit the articles from the database
            val articles: List<Article> = articleDao.getArticlesByCategory(category).first()
            articleSharedPreferences.setCategoryName(category)
            emit(Resource.Success(articles))


        } catch (e: Exception) {
            e.printStackTrace()
            // Emit the cached data (if available)
            val cachedArticles = articleDao.getArticlesByCategory(category).first()
            if (cachedArticles.isNotEmpty()) {
                emit(Resource.Success(cachedArticles))
            } else {

                emit(Resource.Error("check internet connection"))
            }
        }
    }


    override fun getCategories(): List<String> {
        return listOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology"

        )
    }
}