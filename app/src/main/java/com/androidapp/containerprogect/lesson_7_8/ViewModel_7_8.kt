package com.androidapp.containerprogect.lesson_7_8

import android.util.Log
import androidx.lifecycle.ViewModel
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModel_7_8 : ViewModel() {

    private val scope = CoroutineScope(Job())

    init {
        val job = scope.launch {
            Log.d(TAG, "scope = $this")
        }
        Log.d(TAG, "job = $job")
    }
}