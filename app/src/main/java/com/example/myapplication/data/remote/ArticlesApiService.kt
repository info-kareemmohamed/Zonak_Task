package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApiService {
    @GET("top-headlines/category/{category}/us.json")
    suspend fun getArticles(@Path("category") category: String): ArticlesDto
}