package com.facundojaton.marvelcomicschallenge.ui.events_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    companion object {
        val TAG = EventsFragment::class.java.simpleName
    }

    private lateinit var binding : FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEventsBinding.inflate(layoutInflater)
        return binding.root
    }


}