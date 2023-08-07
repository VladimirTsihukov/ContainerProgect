package com.androidapp.containerprogect.lesson_13

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class FragmentLess_13 : Fragment(R.layout.fragment_less_9) {

    private val handler = CoroutineExceptionHandler { context, exception ->
        Log.d(TAG, "first coroutine exception $exception")
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default + handler)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scope.launch {
            TimeUnit.SECONDS.sleep(1)
            Integer.parseInt("a")
        }

        scope.launch {
            repeat(5) {
                TimeUnit.MILLISECONDS.sleep(300)
                Log.d(TAG, "second coroutine isActive $isActive")
            }
        }
    }
}