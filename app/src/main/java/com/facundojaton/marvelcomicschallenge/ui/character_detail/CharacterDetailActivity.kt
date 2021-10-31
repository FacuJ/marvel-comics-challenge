package com.facundojaton.marvelcomicschallenge.ui.character_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.ActivityCharacterDetailBinding
import com.facundojaton.marvelcomicschallenge.databinding.LayoutComicsItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCharacterDetailBinding
    private val args: CharacterDetailActivityArgs by navArgs()
    val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)
        binding.lifecycleOwner = this
        val character = args.character
        viewModel.character = character
        viewModel.getComicsList()
        binding.viewModel = this.viewModel
        binding.executePendingBindings()

        if(character.description.isNullOrBlank()) binding.tvCharacterDescription.text =
            getString(R.string.no_character_description)

        /*character.comics?.items?.let { comics ->
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
        }*/

        viewModel.comicsList.observe(this,{ list->
            list?.let {
                val linearLayout = binding.llComics
                it.forEach { comic ->
                            val comicsBinding : LayoutComicsItemBinding = DataBindingUtil.inflate(
                                layoutInflater,
                                R.layout.layout_comics_item,
                                linearLayout, false)
                            comicsBinding.comic = comic
                            comicsBinding.executePendingBindings()
                            binding.llComics.addView(comicsBinding.root)
                        }
                    }
        })
    }
}