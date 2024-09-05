package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.ArticlesRepository
import javax.inject.Inject

/**
 * Use case for retrieving articles based on a category.
 *
 * This class provides a single source of truth (SST) by getting data from the data layer through
 * the ArticlesRepository. It ensures that all data interactions are centralized and consistent.
 */

class GetArticlesUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {

 suspend operator fun invoke(category: String) = articlesRepository.getArticles(category)

}

