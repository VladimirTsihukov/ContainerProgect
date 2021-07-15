package com.androidapp.containerprogect.scopeLiveData

import androidx.lifecycle.*
import com.androidapp.containerprogect.log
import kotlinx.coroutines.*

class TestScopeLiveData : ViewModel() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val _liveData = MutableLiveData<String>()
    val  liveData = _liveData as LiveData<String>

    val liveDataScope = liveData<String> {
        while (true) {
            delay(1000)
            emit("Work LiveDataScope")
        }
    }

    init {
        log("Launch")

        viewModelScope.launch {
            while (true) {
                delay(1000)
                _liveData.value = "work LiveData"
            }
        }
    }

    fun createViewModelScope() {
        viewModelScope.launch {
            for (i in 1..5) {
                delay(500)
                log("ViewModelScope i=$i")
            }
        }
    }
}