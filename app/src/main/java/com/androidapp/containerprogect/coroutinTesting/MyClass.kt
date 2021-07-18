package com.androidapp.containerprogect.coroutinTesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyClass : ViewModel() {

    var showLoading: Boolean = false

    fun fetchData() {
        showLoading = true

        viewModelScope.launch {
            delay(1000)
            showLoading = false
        }
    }

    suspend fun someMethod(): String {
        delay(5000)
        return "abc"
    }

    suspend fun someMethodTrue(): Boolean {
        delay(2000)
        return true
    }
}