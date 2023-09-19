package com.androidapp.containerprogect.lesson_34

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class FragmentLess_34 : Fragment(R.layout.fragment_less_9) {

    //private val singleThreadDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val mutex = Mutex()
    private var i = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            //var i = AtomicInteger()

            val job1 = launch(Dispatchers.IO) {
                repeat(10_000) {
                    incrementMutex()
                }
            }

            val job2 = launch(Dispatchers.Default) {
                repeat(10_000) {
                    incrementMutex()
                }
            }

            job1.join()
            job2.join()

            Log.d(TAG, "Result i = $i")
        }
    }

    @Synchronized
    private fun increment() {
        i++
    }

    private suspend fun incrementMutex() {
        mutex.withLock {
            i++
        }
    }
}