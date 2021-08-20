package com.rajeev.halodocapp.ui

import androidx.lifecycle.LiveData

interface DataListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onError(s: String)
}