package com.example.filma._core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filma.R
import com.example.filma._core.ui.model.Actor
import com.example.filma.databinding.ActorsRecyclerItemBinding

class ActorListAdapter : RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    private val actorsListDiffer = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<Actor>) = actorsListDiffer.submitList(list)


    inner class ActorViewHolder(private val binding: ActorsRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemActor: Actor) = with(binding) {
            actorName.text = itemActor.name
            asCharacter.text = itemActor.asCharacter
            Glide.with(actorImage.context)
                .load(itemActor.imageUrl)
                .error(R.drawable.ic_actor_no_image)
                .placeholder(R.drawable.ic_actor_no_image)
                .into(actorImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActorsRecyclerItemBinding.inflate(inflater, parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actorsListDiffer.currentList[position]
        holder.bind(itemActor = actor)
    }

    override fun getItemCount(): Int = actorsListDiffer.currentList.size

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem == newItem
        }
    }
}