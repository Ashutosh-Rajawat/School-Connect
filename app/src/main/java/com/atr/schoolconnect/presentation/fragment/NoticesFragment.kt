package com.atr.schoolconnect.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.FragmentChatBinding
import com.atr.schoolconnect.databinding.FragmentNoticesBinding
import com.atr.schoolconnect.presentation.adapter.NoticeAdapter

class NoticesFragment : Fragment() {

    private lateinit var binding: FragmentNoticesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoticesBinding.inflate(layoutInflater, container, false)

        binding.rec.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = NoticeAdapter(requireContext())
        }
        return binding.root
    }


}