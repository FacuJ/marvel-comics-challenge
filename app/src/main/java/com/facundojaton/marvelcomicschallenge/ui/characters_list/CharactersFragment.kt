package com.facundojaton.marvelcomicschallenge.ui.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentCharactersBinding
import com.facundojaton.marvelcomicschallenge.model.MarvelCharacter
import com.facundojaton.marvelcomicschallenge.model.RequestStatus
import com.facundojaton.marvelcomicschallenge.ui.adapters.CharactersListAdapter
import com.facundojaton.marvelcomicschallenge.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    companion object {
        val TAG = CharactersFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by activityViewModels()

    private var listAdapter = CharactersListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.rvCharacters.adapter = listAdapter
        binding.rvCharacters.addOnScrollListener(customScrollListener)
        listAdapter.onCharacterClicked = {
            navigateToCharacterDetail(it)
        }

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
    }

    private val customScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            viewModel.paginateIfNeeded(
                firstVisibleItemPosition,
                visibleItemCount,
                totalItemCount
            )
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                viewModel.onScrollStateTrue()
            }
        }
    }
}