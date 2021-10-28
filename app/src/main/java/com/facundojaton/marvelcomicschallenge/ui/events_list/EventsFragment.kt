package com.facundojaton.marvelcomicschallenge.ui.events_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentEventsBinding
import com.facundojaton.marvelcomicschallenge.model.RequestStatus
import com.facundojaton.marvelcomicschallenge.ui.adapters.EventsListAdapter

class EventsFragment : Fragment() {

    companion object {
        val TAG = EventsFragment::class.java.simpleName
    }

    private lateinit var binding : FragmentEventsBinding
    private val viewModel: EventsViewModel by activityViewModels()
    private var listAdapter = EventsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.rvEvents.adapter = listAdapter
        //rvCharacters.addOnScrollListener(customScrollListener)
        listAdapter.onMarvelEventClicked = {
            // this@CharactersFragment.viewModel.selectCharacter(it)
        }
        /*btnRefresh.setOnClickListener {
            thisViewModel.refresh()
        }

         */
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventsList.observe(viewLifecycleOwner, { list ->
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


}