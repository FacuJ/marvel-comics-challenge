package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.LayoutEventItemBinding
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent

class EventsListAdapter : ListAdapter<
        MarvelEvent,
        EventsListAdapter.MarvelEventViewHolder>(DiffCallback) {

    var onMarvelEventClicked: (marvelEvent: MarvelEvent) -> Unit = { }
    var waiting = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelEventViewHolder {
        return MarvelEventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_event_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MarvelEventViewHolder, position: Int) {
        val marvelEvent = getItem(position)
        holder.bind(marvelEvent)

        holder.binding.ivEvent.setOnClickListener {
            if (!waiting) onMarvelEventClicked(marvelEvent)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarvelEvent>() {
        override fun areItemsTheSame(oldItem: MarvelEvent, newItem: MarvelEvent): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarvelEvent, newItem: MarvelEvent): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class MarvelEventViewHolder(val binding: LayoutEventItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marvelEvent: MarvelEvent) {
            binding.event = marvelEvent
            binding.executePendingBindings()
        }
    }
}