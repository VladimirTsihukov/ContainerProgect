package com.androidapp.containerprogect.ex_02_liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private var _liveData = MutableLiveData<String>()
    var liveData = _liveData as LiveData<String>

    init {
        viewModelScope.launch(Dispatchers.Main) {
            for (x in 0 until 10) {
                delay(1000)
                _liveData.value = x.toString()
                liveData = _liveData
            }
        }
    }
}