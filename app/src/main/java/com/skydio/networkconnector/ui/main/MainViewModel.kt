package com.skydio.networkconnector.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var ssid: String? = null
        set(value) {
            field = value?.ifBlank { null }
        }
    var password: String? = null
        set(value) {
            field = value?.ifBlank { null }
        }

    fun connect() {
        val ssid = this.ssid ?: return showError("Must Provide SSID")
        val password = this.password ?: return showError("Must Provide Password")
    }

    private fun showError(error: String) =
        Toast.makeText(getApplication(), error, Toast.LENGTH_LONG).show()

}