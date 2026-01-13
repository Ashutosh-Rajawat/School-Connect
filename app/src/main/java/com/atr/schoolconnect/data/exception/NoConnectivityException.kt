package com.atr.schoolconnect.data.exception

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No internet connection available. Please try again!"
}