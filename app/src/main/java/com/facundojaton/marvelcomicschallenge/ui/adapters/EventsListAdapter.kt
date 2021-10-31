package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.LayoutComicsItemBinding
import com.facundojaton.marvelcomicschallenge.databinding.LayoutEventItemBinding
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent

class EventsListAdapter : ListAdapter<
        MarvelEvent,
        EventsListAdapter.MarvelEventViewHolder>(DiffCallback) {

    var onMarvelEventClicked: (marvelEvent: MarvelEvent) -> Unit = { }
    var waiting = false
    var openedItem = -1

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

        if (openedItem == position){
            holder.binding.clComicsList.visibility = View.VISIBLE
            holder.binding.ivDropdownArrow.isSelected = true
        }
        holder.binding.ivEvent.apply {
            holder.binding.ivDropdownArrow.setOnClickListener { this.performClick() }
            holder.binding.tvEventDate.setOnClickListener { this.performClick() }
            holder.binding.tvEventTitle.setOnClickListener { this.performClick() }

            this.setOnClickListener {
                if (!waiting) {
                    if (!holder.binding.clComicsList.isVisible) {
                        openedItem = position
                        if (marvelEvent.marvelComics == null) onMarvelEventClicked(marvelEvent)
                    } else {
                        openedItem = -1
                    }
                    toggleComicsVisibility(
                        holder.binding.clComicsList,
                        holder.binding.ivDropdownArrow
                    )
                }
            }
        }

        val linearLayout = holder.binding.llComics
        linearLayout.removeAllViews()
        marvelEvent.marvelComics?.forEach {
            val comicsBinding: LayoutComicsItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(holder.binding.root.context),
                R.layout.layout_comics_item,
                linearLayout, false
            )
            comicsBinding.comic = it
            linearLayout.addView(comicsBinding.root)
        }
    }

    private fun toggleComicsVisibility(clComicsList: ConstraintLayout, imageView: ImageView) {
        clComicsList.visibility = if (clComicsList.visibility == View.VISIBLE) {
            imageView.isSelected = false
            View.GONE
        }
        else {
            imageView.isSelected = true
            View.VISIBLE
        }
    }

    fun updatedEvent(eventId: String) {
        currentList.forEachIndexed { index, marvelEvent ->
            if(marvelEvent.id.toString() == eventId) notifyItemChanged(index)
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
            binding.clComicsList.visibility = View.GONE
            binding.ivDropdownArrow.isSelected = false
            binding.executePendingBindings()
        }
    }

}