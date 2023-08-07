package com.androidapp.containerprogect.lesson_17

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.logCoroutine
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FragmentLess_17 : Fragment(R.layout.fragment_less_9) {

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        scope.launch {

            try {
                twoCoroutine()
            } catch (e: Exception) {
                logCoroutine("ERROR ${e.message}")
            }

            launch(CoroutineName("2_1")) {
                logCoroutine()
            }
            launch(CoroutineName("2_2")) {
                logCoroutine()
            }
        }

    }

    private suspend fun twoCoroutine() {
        coroutineScope {
            launch(CoroutineName("1_1")) {
                logCoroutine()
            }

            launch(CoroutineName("1_2")) {
                Integer.parseInt("a")
            }
        }
    }
}