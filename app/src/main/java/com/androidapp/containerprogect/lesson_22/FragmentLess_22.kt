package com.androidapp.containerprogect.lesson_22

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch

class FragmentLess_22 : Fragment(R.layout.fragment_less_9) {

    private val scope = CoroutineScope(Job())

    private var flow = flow<Int> {
        delay(1_000)
        emit(1)
        delay(1_000)
        emit(2)

        val a = 1 / 0

        delay(1_000)
        emit(3)
        delay(1_000)
        emit(4)
    }

    private val flowString = flow<String> {
        emit("1")
        emit("2")
        emit("a")
        emit("3")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scope.launch {
/*            flow
                .catch { log("ERROR - ${it.message}") }
                .collect {
                    log("collect - $it")
                }*/

/*            flowString
                .map { it.toInt() }
                .catch { log("ERROR - ${it.message}") }
                .collect {
                    log("collect - $it")
                }*/

            flowString
                .map { it.toInt() }
                .retry(2)
                .catch { log("ERROR - ${it.message}") }
                .collect {
                    log("collect - $it")
                }
        }
    }
}