package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.core.ArticleSharedPreferences
import com.example.myapplication.core.Constant.BASE_URL
import com.example.myapplication.core.Constant.DATABASE_NAME
import com.example.myapplication.data.local.ArticleDao
import com.example.myapplication.data.local.ArticleDatabase
import com.example.myapplication.data.remote.ArticlesApiService
import com.example.myapplication.data.repository.ArticlesRepositoryImpl
import com.example.myapplication.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArticlesApiService(): ArticlesApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create(ArticlesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(articlesApiService: ArticlesApiService,articleDao: ArticleDao,articleSharedPreferences: ArticleSharedPreferences): ArticlesRepository {
        return ArticlesRepositoryImpl(articlesApiService,articleDao,articleSharedPreferences)
    }

    @Singleton
    @Provides
    fun provideArticlesDatabase(context: Application): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideArticleSharedPreferences(context: Application): ArticleSharedPreferences {
        return ArticleSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideArticleDao(articleDatabase: ArticleDatabase) = articleDatabase.dao

    @ApplicationContext
    @Provides
    fun provideApplicationContext(application: Application): Application {
        return application
    }


}