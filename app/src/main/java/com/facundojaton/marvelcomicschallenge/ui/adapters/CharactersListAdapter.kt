package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.LayoutCharacterItemBinding
import com.facundojaton.marvelcomicschallenge.model.Character

class CharactersListAdapter : ListAdapter<
        Character,
        CharactersListAdapter.CharacterViewHolder>(DiffCallback) {

    var onCharacterClicked: (character: Character) -> Unit = { }
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

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class CharacterViewHolder(val binding: LayoutCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }
}