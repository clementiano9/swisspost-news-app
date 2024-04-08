package com.clement.newsapp.feature.headlines.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.clement.domain.model.Article
import com.clement.domain.model.Source
import com.clement.newsapp.core.ui.R

@Composable
fun NewsDetails(article: Article?, onBackPress: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = article?.source?.name ?: stringResource(R.string.article)) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .padding(it)
                .scrollable(rememberScrollState(), Orientation.Vertical)
                .fillMaxSize()
        ) {
            if (article?.urlToImage != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = article.urlToImage),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = article?.title ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp
                    ),
                    modifier = Modifier.padding(top = 18.dp, bottom = 24.dp)
                )
                Text(
                    text = article?.description ?: "",
                    Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(text = article?.content ?: "",
                    modifier = Modifier.alpha(0.68f),
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }

    }
}

@Preview
@Composable
fun NewsDetailsPreview() {
    NewsDetails(
        Article(
            title = "Title",
            description = "Description",
            content = "Content",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = "publishedAt",
            author = "author",
            source = Source(name = "source", id = "sourceId")
        ),
        onBackPress = {}
    )
}