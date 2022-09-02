package com.example.filma._core.ui.adapter

import com.example.filma._core.ui.model.Movie

interface RecyclerItemListener {
    fun onItemClick(itemMovie: Movie)
}