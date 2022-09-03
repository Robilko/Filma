package com.example.filma.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filma._core.ui.model.ItemViewState
import com.example.filma.details.domain.DetailsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val useCases: DetailsUseCases) : ViewModel() {

    private val _viewState = MutableStateFlow<ItemViewState>(ItemViewState.Loading)
    val viewState: StateFlow<ItemViewState> = _viewState.asStateFlow()

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            useCases.getMovieDetails(movieId)
                .onSuccess { _viewState.value = ItemViewState.Data(it) }
                .onFailure { _viewState.value = ItemViewState.Error(it.message) }
        }
    }
}