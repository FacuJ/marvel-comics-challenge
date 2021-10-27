package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.model.Character
import com.facundojaton.marvelcomicschallenge.ui.characters_list.CharactersViewModel

@BindingAdapter("charactersList")
fun bindCharactersRecyclerView(
    charactersRecyclerView: RecyclerView,
    data: ArrayList<Character>?
) {
    val adapter = charactersRecyclerView.adapter as CharactersListAdapter
    adapter.submitList(data)
}


@BindingAdapter("marvelApiStatus")
fun bindStatus(progressBar: ProgressBar, status: CharactersViewModel.RequestStatus?) {
    when (status) {
        CharactersViewModel.RequestStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        CharactersViewModel.RequestStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        CharactersViewModel.RequestStatus.SUCCESS -> {
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
}