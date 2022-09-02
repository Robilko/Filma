package com.example.filma._core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filma.R
import com.example.filma._core.ui.model.Movie
import com.example.filma.databinding.FragmentMainRecycleItemBinding

class MovieListAdapter(private val listener: RecyclerItemListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movieListDiffer = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<Movie>) = movieListDiffer.submitList(list)

    inner class MovieViewHolder(private val binding: FragmentMainRecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemMovie: Movie) = with(binding) {
            movieTitle.text = itemMovie.title
            imDbRating.text = itemMovie.imDbRating
            itemView.setOnClickListener { listener.onItemClick(itemMovie) }
            Glide.with(movieImage.context)
                .load(itemMovie.imageUrl)
                .error(R.drawable.ic_image_not_supported)
                .into(movieImage)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentMainRecycleItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieListDiffer.currentList[position]
        holder.bind(itemMovie = movie)
    }

    override fun getItemCount(): Int = movieListDiffer.currentList.size

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>(){
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
        }
    }
}