package com.atr.schoolconnect.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.atr.schoolconnect.R
import com.atr.schoolconnect.data.controller.Rest
import com.atr.schoolconnect.data.viewModels.AuthViewModel
import com.atr.schoolconnect.databinding.ActivityLoginBinding
import com.atr.schoolconnect.domain.rest.ApiResponseListener
import com.atr.schoolconnect.presentation.utilities.CustomSnackbar
import com.atr.schoolconnect.presentation.utilities.PreferenceConnector
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), ApiResponseListener {
    private lateinit var binding: ActivityLoginBinding

    var authViewModel: AuthViewModel? = null
    private var apiResponseListener: ApiResponseListener? = null

    lateinit var sharedPreferences: PreferenceConnector
    private lateinit var rest: Rest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        sharedPreferences = PreferenceConnector(this)
        apiResponseListener = this
        rest = Rest(this)

        // Collect StateFlow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel?.responseState?.collect { apiResponse ->
                    apiResponse?.let { putResponse(it, rest ) }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            if (TextUtils.isEmpty(binding.etEmail.text.toString().trim())) {
                showToast(getString(R.string.enter_email))

            } else if (TextUtils.isEmpty(binding.etPassword.text.toString().trim())) {
                showToast(getString(R.string.enter_password))

            } else {
                val jsonObject = JsonObject().apply {
                    addProperty("mobile", binding.etEmail.text.toString().trim())
                    addProperty("password", binding.etPassword.text.toString().trim())
                }
                authViewModel?.login(jsonObject)

            }
        }
    }

    override fun onLoading() {
        rest.showDialogue()
    }

    override fun onDataRender(jsonObject: JsonObject?) {
        Log.i("onLoading", "onLoading: 2")
    }

    override fun onResponseRender(jsonObject: JsonObject?) {
        sharedPreferences.isLogin = true
        sharedPreferences.baseUrl =
            jsonObject?.getAsJsonObject("data")?.get("base_url")?.asString.toString()
        sharedPreferences.authToken =
            jsonObject?.getAsJsonObject("data")?.get("access_token")?.asString.toString()
        sharedPreferences.userType =
            jsonObject?.getAsJsonObject("data")?.get("user_type")?.asString.toString()
        sharedPreferences.id =
            jsonObject?.getAsJsonObject("data")?.getAsJsonObject("user")?.get("id")?.asString.toString()
        sharedPreferences.name =
            jsonObject?.getAsJsonObject("data")?.getAsJsonObject("user")?.get("name")?.asString.toString()
        sharedPreferences.mobile =
            jsonObject?.getAsJsonObject("data")?.getAsJsonObject("user")?.get("mobile")?.asString.toString()
        sharedPreferences.email =
            jsonObject?.getAsJsonObject("data")?.getAsJsonObject("user")?.get("email")?.asString.toString()


        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        finishAffinity()
    }

    override fun onAuthFailure(message: String?) {
        Log.i("onLoading", "onLoading: 4")
    }

    override fun onServerFailure(message: String?) {
        Log.i("onLoading", "onLoading: 5")
    }

    override fun onOtherFailure(message: String?) {
        Log.i("onLoading", "onLoading: 6")
    }

    override fun onTokenExpire(message: String?, shouldLogout: Boolean) {
        Log.i("onLoading", "onLoading: 7")
    }

    override fun onMembershipExpired(message: String?) {
        Log.i("onLoading", "onLoading: 8")
    }

    fun showToast(message: String) {
        CustomSnackbar.make(findViewById(android.R.id.content), message).show()
    }
}