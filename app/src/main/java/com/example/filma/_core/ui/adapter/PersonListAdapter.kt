package com.example.filma._core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filma.R
import com.example.filma._core.ui.model.Person
import com.example.filma.databinding.ActorsRecyclerItemBinding

class PersonListAdapter : RecyclerView.Adapter<PersonListAdapter.ActorViewHolder>() {

    private val actorsListDiffer = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<Person>) = actorsListDiffer.submitList(list)


    inner class ActorViewHolder(private val binding: ActorsRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemPerson: Person) = with(binding) {
            actorName.text = itemPerson.name
            itemPerson.description?.let {
                asCharacter.text = it
            } ?: run {
                role.visibility = View.GONE
                asCharacter.visibility = View.GONE
            }
            Glide.with(actorImage.context)
                .load(itemPerson.photoUrl)
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
        holder.bind(itemPerson = actor)
    }

    override fun getItemCount(): Int = actorsListDiffer.currentList.size

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem == newItem
        }
    }
}