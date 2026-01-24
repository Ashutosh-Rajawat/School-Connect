package com.atr.schoolconnect.data.repositories

import android.Manifest
import android.app.Application
import androidx.annotation.RequiresPermission
import com.atr.schoolconnect.data.controller.ApiResponse
import com.atr.schoolconnect.data.controller.RestAdapter
import com.atr.schoolconnect.presentation.utilities.QsConstants.Companion.isInternetAvailable
import com.google.gson.JsonObject
import retrofit2.HttpException

class AuthRepo(private val application: Application) {

    // Function to handle API errors
    private fun handleApiError(e: Exception): ApiResponse {
        val errorPayload: String
        val errorStatusCode: Int

        when (e) {
            is HttpException -> {
                errorPayload = e.response()?.errorBody()?.string().toString()
                errorStatusCode = e.code()
            }

            else -> {
                if (isInternetAvailable(application.applicationContext)){
                    errorPayload = e.localizedMessage ?: "Unknown error"
                    errorStatusCode = 500
                }else {
                    errorPayload = e.localizedMessage ?: "Internet not connected"
                    errorStatusCode = 777

                }

            }
        }

        return ApiResponse.error(Throwable(errorPayload), errorStatusCode)
    }

    suspend fun login(params: JsonObject): ApiResponse{
        val restService = RestAdapter.restService

        return try {
            val response = restService.login(params)
            ApiResponse.success(response)
        } catch (e: Exception) {
            handleApiError(e)
        }
    }

    suspend fun getPosts(page:Int): ApiResponse =
        try {
            ApiResponse.success(RestAdapter.restService.getPosts(page))
        } catch (e: Exception) {
            handleApiError(e)
        }

    suspend fun getBanners(): ApiResponse =
        try {
            ApiResponse.success(RestAdapter.restService.getBanners())
        } catch (e: Exception) {
            handleApiError(e)
        }

}