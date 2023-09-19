package com.androidapp.containerprogect.lesson_28

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ViewModel_28 : ViewModel() {

    private val _second = MutableSharedFlow<String>(replay = 10)
    val second = _second.asSharedFlow()

    init {
        viewModelScope.launch {
            repeat(10) {
                _second.emit(it.toString())
                delay(500)
            }
        }
    }
}