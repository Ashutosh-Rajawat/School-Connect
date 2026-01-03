package com.atr.schoolconnect.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atr.schoolconnect.databinding.FragmentChatBinding
import com.atr.schoolconnect.presentation.activity.ChatActivity
import com.atr.schoolconnect.presentation.adapter.ChatListAdapter


class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)


        binding.rec.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ChatListAdapter(requireContext()) {
                startActivity(Intent(requireActivity(), ChatActivity::class.java))
            }
        }

        return binding.root
    }
}