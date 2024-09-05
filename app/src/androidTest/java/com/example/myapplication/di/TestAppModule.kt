package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {


    @Singleton
    @Provides
    @Named("test_db")
    fun provideArticlesDatabase(context: Application): ArticleDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            ArticleDatabase::class.java,
        ).build()
    }


}