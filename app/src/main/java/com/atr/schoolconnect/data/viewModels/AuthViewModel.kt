package com.atr.schoolconnect.data.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atr.schoolconnect.data.controller.ApiResponse
import com.atr.schoolconnect.data.repositories.AuthRepo
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application)  {

    private val repo: AuthRepo = AuthRepo(application)
    private val _responseLiveData = MutableLiveData<ApiResponse>()
    val responseLiveData: LiveData<ApiResponse> get() = _responseLiveData


    fun login(params: JsonObject, type: String) {
        // Launch coroutine in ViewModel scope
        viewModelScope.launch {
            _responseLiveData.value = ApiResponse.loading() // Set loading state
            try {
//                val response = repo.login(params, type) // Suspend function call
//                _responseLiveData.value = response // Update LiveData with success
            } catch (e: Exception) {
                _responseLiveData.value = ApiResponse.error(e, 500, type) // Update LiveData with error
            }
        }
    }

}