package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.MarvelEvent
import com.facundojaton.marvelcomicschallenge.model.RequestStatus

@BindingAdapter("charactersList")
fun bindCharactersRecyclerView(
    charactersRecyclerView: RecyclerView,
    data: ArrayList<MarvelCharacter>?
) {
    val adapter = charactersRecyclerView.adapter as CharactersListAdapter
    adapter.submitList(data)
}

@BindingAdapter("marvelEventsList")
fun bindEventsRecyclerView(
    eventsRecyclerView: RecyclerView,
    data: ArrayList<MarvelEvent>?
) {
    val adapter = eventsRecyclerView.adapter as EventsListAdapter
    adapter.submitList(data)
}


@BindingAdapter("marvelApiStatus")
fun bindStatus(progressBar: ProgressBar, status: RequestStatus?) {
    when (status) {
        RequestStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        RequestStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        RequestStatus.SUCCESS -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImageWithUrl(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context)
        .load(imgUrl)
        .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(imgView)
    imgView.clipToOutline = true
}