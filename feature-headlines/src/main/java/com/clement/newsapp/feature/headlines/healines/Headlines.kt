package com.clement.newsapp.feature.headlines.healines

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.clement.domain.model.Article
import com.clement.newsapp.core.ui.R
import com.clement.newsapp.feature.headlines.component.LoadingPlaceholder

@Composable
fun HeadlinesScreen(viewModel: HeadlinesViewModel = hiltViewModel(), onItemClick: (article: Article) -> Unit) {

    val viewState = viewModel.viewState.collectAsState().value
    LaunchedEffect(key1 = Unit) {
        viewModel.getHeadlines()
    }

    Headlines(
        viewState,
        onItemClick = onItemClick,
        onRefresh = viewModel::refresh
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Headlines(
    viewState: HeadlineViewState,
    onRefresh: () -> Unit,
    onItemClick: (article: Article) -> Unit
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val pullRefreshState = rememberPullRefreshState(viewState.isLoading, onRefresh)

    LaunchedEffect(key1 = viewState.error) {
        if (viewState.error != null) {
            val error = viewState.error
            scaffoldState.snackbarHostState.showSnackbar(error, duration = SnackbarDuration.Long)
        } else {
            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
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
            if (viewState.isLoading && viewState.articles.isEmpty()) {
                LoadingPlaceholder()
            }
            else {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(viewState.articles) { article ->
                        ArticleListItem(article = article, onClick = { onItemClick(article) })
                    }
                }
            }

            val loadingDesc = stringResource(id = com.clement.newsapp.feature.headlines.R.string.headlines_loading)
            PullRefreshIndicator(
                viewState.isLoading,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
                    .semantics { contentDescription = loadingDesc },
                backgroundColor = Color.White,
                contentColor =  MaterialTheme.colors.primary,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HeadlinesPreview() {
    Headlines(
        viewState = HeadlineViewState(
            articles = emptyList(),
            isLoading = true,
            error = null
        ),
        onItemClick = {},
        onRefresh = {}
    )
}
