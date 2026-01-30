package com.atr.schoolconnect.domain.rest

import com.atr.schoolconnect.data.controller.ApiResponse
import com.atr.schoolconnect.data.controller.Rest
import com.atr.schoolconnect.data.controller.Status
import com.atr.schoolconnect.data.exception.AuthenticationException
import com.atr.schoolconnect.data.exception.MembershipExpiredException
import com.atr.schoolconnect.data.exception.ServerException
import com.atr.schoolconnect.data.exception.SevenRoomException
import com.atr.schoolconnect.data.exception.TokenException
import com.google.gson.JsonObject

interface ApiResponseListener {

    fun putResponse(apiResponse: ApiResponse, rest: Rest) {
        when (apiResponse.status) {
            Status.LOADING -> onLoading()
            Status.SUCCESS -> {
                rest.dismissProgressDialog()

                if (apiResponse.data?.asJsonObject?.has("result") == true) {
                    onDataRender(apiResponse.data.getAsJsonObject())
                } else {
                    onResponseRender(apiResponse.data?.getAsJsonObject())
                }
            }

            Status.ERROR -> {
                rest.dismissProgressDialog()

                when (apiResponse.error) {
                    is AuthenticationException -> {
                        onAuthFailure(apiResponse.error.message)
                    }

                    is ServerException -> {
                        onServerFailure(apiResponse.error.message)
                    }

                    is TokenException -> {
                        if (apiResponse.data?.asJsonObject?.get("success") != null && apiResponse.data.asJsonObject?.get(
                                "success"
                            )?.asBoolean == false
                        ) {
                            onTokenExpire(apiResponse.error.message, shouldLogout = true)
                        } else {
                            onTokenExpire(apiResponse.error.message)
                        }
                    }

                    is MembershipExpiredException -> {
                        onMembershipExpired(apiResponse.error.message)
                    }

                    else -> {
                        onOtherFailure(apiResponse.error?.message)
                    }
                }
            }

            else -> {}
        }
    }

    fun onLoading()
    fun onDataRender(jsonObject: JsonObject?)
    fun onResponseRender(jsonObject: JsonObject?)
    fun onAuthFailure(message: String?)
    fun onServerFailure(message: String?)
    fun onOtherFailure(message: String?)
    fun onTokenExpire(message: String?, shouldLogout: Boolean = false)
    fun onMembershipExpired(message: String?)
}