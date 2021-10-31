package com.facundojaton.marvelcomicschallenge.ui.character_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.ActivityCharacterDetailBinding
import com.facundojaton.marvelcomicschallenge.databinding.LayoutComicsItemBinding


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

        if(character.description.isNullOrBlank()) binding.tvCharacterDescription.text =
            getString(R.string.no_character_description)

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