package com.androidapp.containerprogect.lesson_27

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLess_27 : Fragment(R.layout.fragment_less_9) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        asynchronousWork()

    }

    private fun asynchronousWork() {
        lifecycleScope.launch {

            val result1 = async { load1() }
            val result2 = async { load2() }

            Log.d(TAG, "${result1.await()} - ${result2.await()}")
        }
    }

    private fun synchronousWork() {
        lifecycleScope.launch {

            val result1 = withContext(Dispatchers.Default) { load1() }
            val result2 = withContext(Dispatchers.Default) { load2() }

            Log.d(TAG, result1 + result2)
        }
    }

    suspend fun load1(): String {
        delay(1_000)
        Log.d(TAG, "load 1")
        return "load 1"
    }

    suspend fun load2(): String {
        delay(1_000)
        Log.d(TAG, "load 2")
        return "load 2"
    }

}