package com.facundojaton.marvelcomicschallenge.ui.adapters

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter

import androidx.databinding.DataBindingUtil
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.LayoutComicsItemBinding
import com.facundojaton.marvelcomicschallenge.model.ComicSummary

class ComicsListAdapter(context: Context, private val resourceLayout: Int, items: ArrayList<ComicSummary>) :
    ArrayAdapter<ComicSummary>(context, resourceLayout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val comic = getItem(position)
        var convertView = convertView
        val binding: LayoutComicsItemBinding
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.layout_comics_item, parent, false
            )
            convertView = binding.root
        } else {
            binding = convertView.tag as LayoutComicsItemBinding
        }
        binding.tvComicName.text = comic?.name
        binding.tvComicYear.text = comic?.getYearWithoutName()
        convertView.tag = binding
        return convertView
    }

}