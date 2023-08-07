package com.androidapp.containerprogect.lesson_23

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay

class ViewModelLess23 : ViewModel() {

    init {
/*        viewModelScope.launch {
            log("launch")
            while (true) {
                delay(1_000)
                log("Work")
            }
        }*/
    }

    val liveData = liveData<String> {
        while (true) {
            delay(1_000)
            emit("Work")
        }
    }
}