package com.facundojaton.marvelcomicschallenge.ui.characters_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.ActivityCharacterDetailBinding
import com.facundojaton.marvelcomicschallenge.databinding.LayoutComicsItemBinding
import com.facundojaton.marvelcomicschallenge.model.ComicSummary
import com.facundojaton.marvelcomicschallenge.ui.adapters.ComicsListAdapter
import com.facundojaton.marvelcomicschallenge.ui.home.HomeFragmentDirections

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCharacterDetailBinding
    val args: CharacterDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)
        binding.lifecycleOwner = this
        val character = args.character
        binding.character = character
        binding.executePendingBindings()
        character.comics?.items?.let { comics ->
            val linearLayout = binding.llComics
            comics.forEach {
                val comicsBinding : LayoutComicsItemBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.layout_comics_item,
                    linearLayout, false)
                comicsBinding.comic = it
                comicsBinding.executePendingBindings()
                binding.llComics.addView(comicsBinding.root)
            }
        }
    }
}