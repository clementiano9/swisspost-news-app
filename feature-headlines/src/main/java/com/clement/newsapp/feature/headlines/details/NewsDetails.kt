package com.clement.newsapp.feature.headlines.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
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
import com.clement.newsapp.feature.headlines.extensions.formatDate

@Composable
fun NewsDetails(article: Article?, onBackPress: () -> Unit) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(article?.url)) }
    val shareIntent = Intent.createChooser(intent, null)

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
                actions = {
                    IconButton(onClick = { context.startActivity(shareIntent) }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .padding(it)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
//            if (article?.urlToImage != null) {
                Image(
                    painter = if (article?.urlToImage != null) rememberAsyncImagePainter(model = article.urlToImage)
                    else painterResource(id = R.drawable.empty_news),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
//            }
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
                val context = LocalContext.current
                Text(
                    text = "${article?.source?.name} • ${article?.publishedAt?.formatDate(context)}",
                    Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.caption,
                    fontSize = 14.sp,
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