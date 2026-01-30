package com.atr.schoolconnect.data.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atr.schoolconnect.data.controller.ApiResponse
import com.atr.schoolconnect.data.controller.Status
import com.atr.schoolconnect.data.repositories.AuthRepo
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import okhttp3.Dispatcher

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = AuthRepo(application)

    private val _responseState = MutableStateFlow<ApiResponse?>(null)
    val responseState: StateFlow<ApiResponse?> = _responseState

    fun login(params: JsonObject) {
        viewModelScope.launch {
            _responseState.value = ApiResponse.loading()
            try {
                val response = repo.login(params)
                _responseState.value = response
            } catch (e: Exception) {
                _responseState.value = ApiResponse.error(e, 500)
            }
        }
    }

    /* ---------------- BANNERS (one time) ---------------- */

    fun loadBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            _responseState.value = ApiResponse.loading()

            try {
                val response = repo.getBanners()

                val json = JsonObject().apply {
                    addProperty("apiType", "banners")                // api name
                    add("response", Gson().toJsonTree(response)) // FULL response
                }


                _responseState.value = ApiResponse.success(json)

            } catch (e: Exception) {
                _responseState.value = ApiResponse.error(e, 500)
            }
        }
    }

    /* ---------------- POSTS (pagination) ---------------- */

    fun loadPosts(page: Int,token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            _responseState.value = ApiResponse.loading()

            try {
                val response = repo.getPosts(page,token)
                val json = JsonObject().apply {
                    addProperty("apiType", "posts")                // api name
                    add("response", Gson().toJsonTree(response)) // FULL response
                }
                _responseState.value = ApiResponse.success(json)

            } catch (e: Exception) {
                _responseState.value = ApiResponse.error(e, 500)
            }
        }
    }

}
