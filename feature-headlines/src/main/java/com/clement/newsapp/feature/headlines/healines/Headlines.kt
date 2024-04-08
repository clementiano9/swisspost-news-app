package com.clement.newsapp.feature.headlines.healines

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.clement.domain.model.Article
import com.clement.newsapp.core.ui.R
import com.clement.newsapp.feature.headlines.component.LoadingPlaceholder
import com.clement.newsapp.feature.headlines.extensions.formatDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Headlines(onItemClick: (article: Article) -> Unit) {
    val viewModel: HeadlinesViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState()

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val pullRefreshState = rememberPullRefreshState(viewState.value.isLoading, viewModel::refresh)

    LaunchedEffect(key1 = Unit) {
        viewModel.getHeadlines()
    }

    LaunchedEffect(key1 = viewState.value.error) {
        if (viewState.value.error != null) {
            val error = viewState.value.error!!
            scaffoldState.snackbarHostState.showSnackbar(error)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name), ) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
            )
        },
        scaffoldState = scaffoldState
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center
        ) {
            if (viewState.value.isLoading && viewState.value.articles.isEmpty()) {
                LoadingPlaceholder()
            }
            else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(viewState.value.articles) { article ->
                        ArticleItem(article = article, onClick = { onItemClick(article) })
                    }
                }
            }

            PullRefreshIndicator(
                viewState.value.isLoading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter),
                backgroundColor = Color.White,
                contentColor =  MaterialTheme.colors.primary,
            )
        }
    }

}

/**
 * Article Item with image and headline
 */
@Composable
fun ArticleItem(article: Article, onClick: (Article) -> Unit) {
    val localContext = LocalContext.current
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
                text = article.publishedAt.formatDate(localContext),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.alpha(0.45f),
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeadlinesPreview() {
    Headlines(onItemClick = {})
}
