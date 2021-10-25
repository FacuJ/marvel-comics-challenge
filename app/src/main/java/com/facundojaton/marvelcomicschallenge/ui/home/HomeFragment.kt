package com.facundojaton.marvelcomicschallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentHomeBinding
import com.facundojaton.marvelcomicschallenge.ui.adapters.ViewPagerAdapter
import com.facundojaton.marvelcomicschallenge.ui.characters_list.CharactersFragment
import com.facundojaton.marvelcomicschallenge.ui.events_list.EventsFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    companion object {
        val TAG = HomeFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            CharactersFragment(), EventsFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.vpHome.adapter = adapter

        binding.tabLayout

        TabLayoutMediator(binding.tabLayout, binding.vpHome) { tab, position ->
            if (position == 0) tab.setCustomView(R.layout.custom_tab_view_item)
            else tab.setCustomView(R.layout.custom_tab_view_item_calendar)
        }.attach()
    }
}