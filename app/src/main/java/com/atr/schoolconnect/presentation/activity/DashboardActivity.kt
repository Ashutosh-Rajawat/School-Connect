package com.atr.schoolconnect.presentation.activity

import android.graphics.Color
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.SoundEffectConstants
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment
//        binding.bottomNav.setupWithNavController(navHostFragment.navController)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)


        // Apply insets properly
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // ✅ Apply TOP inset to content only
            binding.navHostFragmentContainer.setPadding(
                0,
                systemBars.top,
                0,
                0
            )

            // ✅ Apply BOTTOM inset ONLY to bottom nav
            binding.bottomNav.setPadding(
                0,
                0,
                0,
                systemBars.bottom
            )

            insets
        }
        window.statusBarColor = Color.WHITE

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        // Play feedback whenever the destination actually changes, without overriding
        // the NavigationUI listener that handles fragment navigation.
        navController.addOnDestinationChangedListener { _, _, _ ->
            playFeedback()
        }

    }
    fun playFeedback() {
        binding.bottomNav.performHapticFeedback(
            HapticFeedbackConstants.KEYBOARD_TAP
        )
        binding.bottomNav.playSoundEffect(
            SoundEffectConstants.CLICK
        )
    }

}
