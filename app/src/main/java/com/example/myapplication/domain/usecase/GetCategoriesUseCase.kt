package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.ArticlesRepository
import javax.inject.Inject

/**
 * Use case for retrieving the list of article categories.
 *
 * This class retrieves category data from the ArticlesRepository.
 */

class GetCategoriesUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {
    operator  fun  invoke() = articlesRepository.getCategories()

}
