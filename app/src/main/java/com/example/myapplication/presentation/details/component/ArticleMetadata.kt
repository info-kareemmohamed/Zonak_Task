package com.example.myapplication.presentation.details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.myapplication.R

@Composable
fun ArticleMetadata(
    source: String,
    author: String,
    publishedAt: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = source,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(
                id = R.color.text_title
            ),
        )
        Text(
            text = author,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(
                id = R.color.text_title
            ),
        )
        Text(
            text = publishedAt,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(
                id = R.color.text_title
            ),
        )
    }
}