package com.example.filma._core.ui.model

sealed class ItemViewState {
    object Loading : ItemViewState()
    data class Data(val data: MovieDetails) : ItemViewState()
    data class Error(val message: String?) : ItemViewState()
}