package com.example.filma._core.ui.model.state

import com.example.filma._core.ui.model.DetailsMovie

sealed class ItemViewState {
    object Loading : ItemViewState()
    data class Data(val data: DetailsMovie) : ItemViewState()
    data class Error(val message: String?) : ItemViewState()
}