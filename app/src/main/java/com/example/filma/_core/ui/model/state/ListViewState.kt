package com.example.filma._core.ui.model.state

import com.example.filma._core.ui.model.Movie

sealed class ListViewState {
    object Loading : ListViewState()
    data class Data(val data: List<Movie>) : ListViewState()
    data class Error(val message: String?) : ListViewState()
}