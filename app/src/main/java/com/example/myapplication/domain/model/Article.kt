package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "articles")
data class Article(
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    @PrimaryKey val url: String,
    val image: String,
    val publishedAt: String,
    val content: String,
    val category: String
)