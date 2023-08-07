package com.androidapp.containerprogect.lesson_14

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import com.androidapp.containerprogect.log
import com.androidapp.containerprogect.repeatIsActive
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class FragmentLess_14 : Fragment(R.layout.fragment_less_9) {

    private val handler = CoroutineExceptionHandler { context, exception ->
        Log.d(TAG, "$exception was handled in Coroutine_${context[CoroutineName]?.name}")
    }

    private val scope = CoroutineScope(Job() + Dispatchers.Default + handler)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scope.launch(CoroutineName("1")) {

            launch(CoroutineName("1_1")) {
                TimeUnit.MILLISECONDS.sleep(1000)
                log("EXCEPTION")
                Integer.parseInt("a")
            }

            launch(CoroutineName("1_2")) { repeatIsActive() }

            repeatIsActive()
        }

        scope.launch(CoroutineName("2")) {

            launch(CoroutineName("2_1")) { repeatIsActive() }

            launch(CoroutineName("2_2")) { repeatIsActive() }

            repeatIsActive()
        }
    }
}