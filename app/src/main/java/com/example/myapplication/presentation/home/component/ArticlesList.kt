package com.example.myapplication.presentation.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.Article


@Composable
fun ArticlesList(
    articles: List<Article>,
    onItemClick: (Article) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(20.dp)) {
        items(articles) { article ->
            ArticleItem(article = article) {
               onItemClick(article)
            }
        }
    }

}