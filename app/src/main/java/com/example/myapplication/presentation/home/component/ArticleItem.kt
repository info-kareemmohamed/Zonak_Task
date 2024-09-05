package com.example.myapplication.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.Article
import com.example.myapplication.presentation.common.component.ImageFromUrl

@Composable
fun ArticleItem(article: Article, onItemClick: () -> Unit) {
    Card(
        onClick = onItemClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp)
    ) {


        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.3f))
                .fillMaxSize() // Make the Box fill the Card size
        ) {
            ImageFromUrl(
                url = article.image,
                modifier = Modifier
                    .fillMaxSize()
            )


            Text(
                text = article.title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(5.dp)
                    .align(Alignment.BottomStart)
            )
        }


    }
}
