package com.atr.schoolconnect.presentation.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.FragmentHomeBinding
import com.atr.schoolconnect.presentation.adapter.HomeSocialAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var adapter: HomeSocialAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        binding.rec.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = HomeSocialAdapter(requireContext())
        }


        return binding.root
    }


}