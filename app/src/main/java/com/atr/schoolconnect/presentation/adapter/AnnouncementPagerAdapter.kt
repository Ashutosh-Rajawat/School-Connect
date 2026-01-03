package com.atr.schoolconnect.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.atr.schoolconnect.presentation.fragment.AcademicsFragment
import com.atr.schoolconnect.presentation.fragment.CalendarFragment
import com.atr.schoolconnect.presentation.fragment.NoticesFragment

class AnnouncementPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NoticesFragment()
            1 -> AcademicsFragment()
            else -> CalendarFragment()
        }
    }
}