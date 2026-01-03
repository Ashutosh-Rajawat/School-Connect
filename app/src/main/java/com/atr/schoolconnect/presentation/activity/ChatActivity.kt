package com.atr.schoolconnect.presentation.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.ActivityChatBinding
import com.atr.schoolconnect.presentation.adapter.ChatAdapter
import com.atr.schoolconnect.presentation.adapter.FeePaymentAdapter

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.root.setPadding(
                0,
                systemBars.top,
                0,
                systemBars.bottom
            )
            insets
        }
        window.statusBarColor = Color.WHITE
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.rec.apply {
            layoutManager =
                LinearLayoutManager(this@ChatActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ChatAdapter(this@ChatActivity)
        }

    }
}