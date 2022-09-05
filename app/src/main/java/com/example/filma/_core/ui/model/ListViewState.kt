package com.example.filma._core.ui.model

sealed class ListViewState {
    object Loading : ListViewState()
    data class Data(val data: List<MovieKin>) : ListViewState()
    data class Error(val message: String?) : ListViewState()
}