package com.clement.newsapp.feature.headlines.healines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clement.domain.model.Article
import com.clement.domain.usecase.GetHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewState for Headline screen
 */
data class HeadlineViewState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean,
    val error: String? = null
)

/**
 * ViewModel for Headline screen
 */
@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val getHeadlinesUseCase: GetHeadlinesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _viewState: MutableStateFlow<HeadlineViewState> =
        MutableStateFlow(HeadlineViewState(isLoading = true))
    val viewState = _viewState.asStateFlow()

    /**
     * Fetch headlines from the API
     */
    fun getHeadlines() {
        viewModelScope.launch(dispatcher) {
            try {
                val results = getHeadlinesUseCase()
                _viewState.update { it.copy(articles = results, isLoading = false) }
            } catch (e: Exception) {
                _viewState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun refresh() {
        _viewState.update { it.copy(isLoading = true) }
        getHeadlines()
    }
}