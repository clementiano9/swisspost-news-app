package com.clement.newsapp.ui.healines

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.clement.model.Article
import com.clement.newsapp.R
import com.clement.newsapp.extensions.formatDate

@Composable
fun Headlines(onItemClick: (article: Article) -> Unit) {
    val viewModel: HeadlinesViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name), ) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)) {
            LazyColumn {
                items(viewState.value.articles) { article ->
                    ArticleItem(article = article, onClick = { onItemClick(article) })
                }
            }
        }
    }

}

/**
 * Article Item with image and headline
 */
@Composable
fun ArticleItem(article: Article, onClick: (Article) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clickable { onClick(article) }
    ) {

        Image(
            painter = if (article.urlToImage != null) rememberAsyncImagePainter(model = article.urlToImage)
            else painterResource(id = R.drawable.empty_news),
            contentDescription = null,
            Modifier
                .padding(end = 16.dp)
                .size(100.dp)
                .align(Alignment.CenterVertically)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = if (article.urlToImage != null) ContentScale.Crop else ContentScale.Inside
        )

        Column {
            Text(
                text = article.title,
                color = Color.Black,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .alpha(0.8f),
                fontWeight = FontWeight.Medium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = article.publishedAt.formatDate(),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.alpha(0.45f),
            )

        }
    }
}
