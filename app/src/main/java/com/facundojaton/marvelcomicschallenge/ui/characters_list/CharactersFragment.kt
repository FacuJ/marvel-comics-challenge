package com.facundojaton.marvelcomicschallenge.ui.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentCharactersBinding
import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.RequestStatus
import com.facundojaton.marvelcomicschallenge.ui.adapters.CharactersListAdapter
import com.facundojaton.marvelcomicschallenge.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by activityViewModels()

    private var listAdapter = CharactersListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvCharacters.adapter = listAdapter
        //rvCharacters.addOnScrollListener(customScrollListener)
        listAdapter.onCharacterClicked = {
            navigateToCharacterDetail(it)
        }
        /*btnRefresh.setOnClickListener {
            thisViewModel.refresh()
        }*/

        return binding.root
    }

    private fun navigateToCharacterDetail(marvelCharacter: MarvelCharacter) {
        this.findNavController().navigate(
            HomeFragmentDirections.actionCharactersFragmentToCharacterDetailActivity(
                marvelCharacter
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.marvelCharacterList.observe(viewLifecycleOwner, { list ->
            list?.let {
                it.size
            }
        })

        viewModel.status.observe(viewLifecycleOwner, { status ->
            when (status) {
                RequestStatus.ERROR -> {
                    listAdapter.waiting = false
                    Toast.makeText(
                        context?.applicationContext,
                        R.string.error_fetching_characters,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                RequestStatus.LOADING -> {
                    listAdapter.waiting = true
                }
                else -> {
                    listAdapter.waiting = false
                }
            }
        })
/*
        viewModel.selectedCharacter.observe(viewLifecycleOwner, { detail ->
            detail?.let {
                navigateToDetail(it)
            }
        })*/
    }


/*

    private fun navigateToCharacterDetail(detail: CharacterDetail) {
        seriesListViewModel.navigateToDetailsFinished()
        this.findNavController().navigate(SeriesListFragmentDirections.actionShowDetail(detail))
    }
*/

    companion object {
        val TAG = CharactersFragment::class.java.simpleName
    }
}