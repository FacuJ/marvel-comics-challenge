package com.facundojaton.marvelcomicschallenge.ui.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentCharactersBinding
import com.facundojaton.marvelcomicschallenge.model.Character
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

    private fun navigateToCharacterDetail(character: Character) {
        this.findNavController().navigate(
            HomeFragmentDirections.actionCharactersFragmentToCharacterDetailActivity(
                character
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.characterList.observe(viewLifecycleOwner, { list ->
            list?.let {
                it.size
            }
        })

        viewModel.status.observe(viewLifecycleOwner, { status ->
            when (status) {
                CharactersViewModel.RequestStatus.ERROR -> {
                    listAdapter.waiting = false
                    Toast.makeText(
                        context?.applicationContext,
                        R.string.error_fetching_characters,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                CharactersViewModel.RequestStatus.LOADING -> {
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


/*private val customScrollListener = object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as GridLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        seriesListViewModel.paginateIfNeeded(
            firstVisibleItemPosition,
            visibleItemCount,
            totalItemCount
        )
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            seriesListViewModel.onScrollStateTrue()
        }
    }
}*/

    companion object {
        val TAG = CharactersFragment::class.java.simpleName
    }
}