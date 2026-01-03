package com.atr.schoolconnect.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.FragmentAnnouncementBinding
import com.atr.schoolconnect.presentation.adapter.AnnouncementPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class AnnouncementFragment : Fragment() {

    private lateinit var binding: FragmentAnnouncementBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncementBinding.inflate(layoutInflater, container, false)

        val adapter = AnnouncementPagerAdapter(this)
        binding.viewPager.adapter = adapter

        val tabTitles = listOf("Notices", "Academics", "Calendar")

        // ❌ Disable swipe
        binding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_tab, null)

            tabView.findViewById<TextView>(R.id.tvTab).text = tabTitles[position]
            tab.customView = tabView
        }.attach()
        return binding.root
    }

}