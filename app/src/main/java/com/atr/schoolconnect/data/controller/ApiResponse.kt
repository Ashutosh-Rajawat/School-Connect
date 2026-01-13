package com.atr.schoolconnect.data.controller

import com.atr.schoolconnect.data.exception.NoConnectivityException
import com.atr.schoolconnect.data.exception.ServerException
import com.atr.schoolconnect.data.exception.TokenException
import com.atr.schoolconnect.presentation.utilities.QsConstants
import com.google.gson.JsonElement

data class ApiResponse(

    val status: Status,

    val data: JsonElement? = null,

    val error: Throwable? = null,

    ) {

    companion object {

        //var isCon: Boolean = false

        fun loading(): ApiResponse {
            return ApiResponse(Status.LOADING)
        }

        fun success(data: JsonElement, type: String): ApiResponse {
            if (data.isJsonNull) {
                return ApiResponse(Status.ERROR, null, NullPointerException("Null response occurs"))
            }

            var statusCode = 0;
            statusCode = when {
                data.asJsonObject.has("status") && !data.asJsonObject.get("status").isJsonNull -> data.asJsonObject.get(
                    "status"
                ).asInt

                else -> 0
            }
            var message = ""
            message = when {
                data.asJsonObject.has("message") && !data.asJsonObject.get("message").isJsonNull -> data.asJsonObject.get(
                    "message"
                ).asString

                else -> "No message available"  // Default fallback message
            }

            when (statusCode) {
                401 -> {
                    // For Unauthorised
                    ApiResponse(Status.ERROR, data, TokenException(message))

                }

                500, 402, 403, 406, 203, 400, 405, 409, 404 -> {
                    return ApiResponse(Status.ERROR, data, ServerException(message))
                }

                503 -> {
                    // For Server maintenance
                }
            }
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable, statusCode: Int, type: String): ApiResponse {
            return if (error is NoConnectivityException) {
                ApiResponse(
                    Status.ERROR,
                    null,
                    ServerException("No internet connection available. Please try again!")
                )
            } else {
                var errorMessage = ""
                try {
                    val errorJson: Map<String, Any>? =
                        QsConstants.jsonToMap(error.message.toString())
                            ?.mapKeys { it.key.lowercase() } // Convert keys to lowercase

                    errorMessage = errorJson?.get("message")?.toString()
                        ?: errorJson?.get("msg")?.toString()
                                ?: "Unknown error"
                } catch (e: Exception) {
                }
                return when (statusCode) {

                    401 -> {
                        ApiResponse(Status.ERROR, null, TokenException(errorMessage))
                    }

                    400, 405, 409, 404, 500, 402, 403, 406 -> {
                        ApiResponse(Status.ERROR, null, ServerException(errorMessage))
                    }

                    else -> ApiResponse(
                        Status.ERROR,
                        null,
                        ServerException(error.localizedMessage ?: "An error occurred")
                    )
                }
            }
        }
    }
}