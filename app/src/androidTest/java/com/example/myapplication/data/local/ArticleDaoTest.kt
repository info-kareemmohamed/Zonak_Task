package com.example.myapplication.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.domain.model.Article
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@HiltAndroidTest
class ArticleDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var articleDb: ArticleDatabase
    private lateinit var dao: ArticleDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = articleDb.dao
    }

    @Test
    fun getArticles_returnsEmptyList() = runTest {
        // When
        val articles = dao.getArticles().first()
        // Then
        assertThat(articles).isEmpty()
    }

    @Test
    fun getArticlesByCategory_returnsNotEmptyList() = runTest {
        // Given
        val articles = listOf(
            Article("Source1", "Author1", "Title1", "Description1", "url1", "Image1", "2024-09-05", "Content1", "Category1"),
            Article("Source2", "Author2", "Title2", "Description2", "url2", "Image2", "2024-09-06", "Content2", "Category1")
        )
        dao.insertArticles(articles)

        // When
        val articlesFromDb = dao.getArticlesByCategory("Category1").first()

        // Then
        assertThat(articlesFromDb).isEqualTo(articles)
    }

    @Test
    fun insertArticles() = runTest {
        // Given
        val articlesToInsert = listOf(
            Article("Source1", "Author1", "Title1", "Description1", "url1", "Image1", "2024-09-05", "Content1", "Category1"),
            Article("Source2", "Author2", "Title2", "Description2", "url2", "Image2", "2024-09-06", "Content2", "Category1")
        )

        // When
        dao.insertArticles(articlesToInsert)

        // Then
        val articlesFromDb = dao.getArticles().first()
        assertThat(articlesFromDb).isEqualTo(articlesToInsert)
    }

    @Test
    fun deleteArticles() = runTest {
        // Given
        val articlesToInsert = listOf(
            Article("Source1", "Author1", "Title1", "Description1", "url1", "Image1", "2024-09-05", "Content1", "Category1"),
            Article("Source2", "Author2", "Title2", "Description2", "url2", "Image2", "2024-09-06", "Content2", "Category1")
        )
        dao.insertArticles(articlesToInsert)

        // When
        dao.deleteAllArticles()
        val articlesFromDb = dao.getArticles().first()

        // Then
        assertThat(articlesFromDb).isEmpty()
    }

    @After
    fun teardown() {
        articleDb.close()
    }


}