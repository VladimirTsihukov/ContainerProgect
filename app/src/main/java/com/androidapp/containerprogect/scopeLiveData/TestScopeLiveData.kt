package com.androidapp.containerprogect.scopeLiveData

import androidx.lifecycle.*
import com.androidapp.containerprogect.log
import kotlinx.coroutines.*

class TestScopeLiveData : ViewModel() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val _liveData = MutableLiveData<String>()
    var liveData = _liveData as LiveData<String>

    val liveDataScope = liveData<String> {
        while (true) {
            delay(1000)
            emit("Work LiveDataScope")
        }
    }

    init {
        //testCoroutineLiveData()
        testErrorViewModelScope()
    }

    private fun testErrorViewModelScope() {
        viewModelScope.launch {

            log("START COROUTINE")

            val result1 = async {
                var result = 0
                for (i in 1..5) {
                    delay(500)
                    log("Result1 = $i")
                    result = i
                    if (i == 3) result = i / 0

                }
                result
            }

            val result2 = async {
                var result = 0
                for (i in 11..15) {
                    delay(500)
                    log("Result2 = $i")
                    result = i
                }
                result
            }

            log("Result1 = ${result1.await()}, Result2 = ${result2.await()}")

            log("STOP COROUTINE")
        }
    }

    private fun testCoroutineLiveData() {
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