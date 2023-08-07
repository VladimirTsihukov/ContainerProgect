package com.androidapp.containerprogect.lesson_19

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FragmentLess_19: Fragment(R.layout.fragment_less_9) {

    private val flow = flow {
        for (i in 1..10) {
            delay(1_000)
            emit(i)
        }
    }

    private val scope = CoroutineScope(Job())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scope.launch {
            flow.collect {
                log("Emit $it")
            }
        }
    }
}