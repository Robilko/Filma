package com.example.filma.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filma._core.ui.model.ListViewState
import com.example.filma.main.domain.MainUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val useCases: MainUseCases) : ViewModel() {

    private val _viewState = MutableStateFlow<ListViewState>(ListViewState.Loading)
    val viewState: StateFlow<ListViewState> = _viewState.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            useCases.getMostPopularMoviesList()
                .onSuccess { _viewState.value = ListViewState.Data(it) }
                .onFailure { _viewState.value = ListViewState.Error(message = it.message) }
        }
    }
}