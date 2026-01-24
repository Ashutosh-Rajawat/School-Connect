package com.atr.schoolconnect.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.atr.schoolconnect.databinding.ActivitySplashBinding
import com.atr.schoolconnect.presentation.utilities.PreferenceConnector

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    lateinit var sharedPreferences: PreferenceConnector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = PreferenceConnector(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPreferences.isLogin){
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))

            }else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))

            }
            finishAffinity()
        }, 2000)


    }
}