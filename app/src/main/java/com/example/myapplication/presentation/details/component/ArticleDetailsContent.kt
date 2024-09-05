package com.example.myapplication.presentation.details.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.core.formatDateTime
import com.example.myapplication.domain.model.Article
import com.example.myapplication.presentation.common.component.ImageFromUrl
import com.example.myapplication.presentation.ui.theme.Pink100


@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    article: Article,
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = onBrowsingClick,
            onShareClick = onShareClick,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),

            ) {

            ImageFromUrl(article.image,Modifier
                .fillMaxWidth()
                .height(248.dp)
                .clip(MaterialTheme.shapes.medium))
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(
                    id = R.color.text_title
                )
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Pink100,
                modifier = Modifier.clickable {
                    onBrowsingClick()
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            ArticleMetadata(article.source, article.author, article.publishedAt.formatDateTime())


        }
    }
}





