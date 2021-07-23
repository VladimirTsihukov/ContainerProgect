package com.androidapp.containerprogect.ex_03_viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelFragments : ViewModel() {

    private val liveData = MutableLiveData<String>()
    private var current = 0

    fun setLiveData(text: String) {
        liveData.value = "$text ${++current}"
    }

    fun getLiveData(): LiveData<String> = liveData
}