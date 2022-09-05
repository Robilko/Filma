package com.example.filma._core.ui.adapter

import com.example.filma._core.ui.model.MovieKin

interface RecyclerItemListener {
    fun onItemClick(itemMovie: MovieKin)
}