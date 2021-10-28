package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.LayoutCharacterItemBinding
import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter

class CharactersListAdapter : ListAdapter<
        MarvelCharacter,
        CharactersListAdapter.CharacterViewHolder>(DiffCallback) {

    var onCharacterClicked: (marvelCharacter: MarvelCharacter) -> Unit = { }
    var waiting = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        return CharacterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_character_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.binding.ivCharacter.setOnClickListener {
            if (!waiting) onCharacterClicked(character)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class CharacterViewHolder(val binding: LayoutCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marvelCharacter: MarvelCharacter) {
            binding.character = marvelCharacter
            binding.executePendingBindings()
        }
    }
}