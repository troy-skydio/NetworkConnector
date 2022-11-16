package com.skydio.networkconnector.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skydio.networkconnector.domain.ConnectToNetworkUseCase

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var ssid: String? = null
        set(value) {
            field = value?.ifBlank { null }
        }

    var password: String? = null
        set(value) {
            field = value?.ifBlank { null }
        }

    private val requestCallbackSteps = mutableListOf<String>()
    private val _requestCallbackStepsLiveData = MutableLiveData("")
    val requestCallbackStepsLiveData: LiveData<String> = _requestCallbackStepsLiveData

    private val connectToNetworkUseCase = ConnectToNetworkUseCase(app)

    fun connect() {
        clearCallbackSteps()
        val ssid = this.ssid ?: return showError("Must Provide SSID")
        val password = this.password ?: return showError("Must Provide Password")
        connectToNetworkUseCase(ssid, password, ::appendCallbackStep)
    }

    private fun appendCallbackStep(step: String) {
        requestCallbackSteps.add(step)
        val steps = requestCallbackSteps.joinToString(separator = "\n\n")
        _requestCallbackStepsLiveData.postValue(steps)
    }

    private fun clearCallbackSteps() {
        requestCallbackSteps.clear()
        _requestCallbackStepsLiveData.value = ""
    }

    private fun showError(error: String) =
        Toast.makeText(getApplication(), error, Toast.LENGTH_LONG).show()

}
