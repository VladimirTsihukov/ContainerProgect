package com.androidapp.containerprogect.lesson_20

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class FragmentLess_20 : Fragment(R.layout.fragment_less_9) {

    private val flowList = flowOf("a", "b", "c", "d")

    private val flowInt = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    private val flowString = flow {
        emit("abc")
        emit("dgf")
        emit("lmn")
    }.transform { value ->
        value.forEach { emit(it) }
    }

    private val scope = CoroutineScope(Job())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scope.launch {
            //log(flowInt.join())
            /*            flowString.collect {
                            log(it.toString())
                        }*/
            flowList.collect {
                log(it)
            }
        }
    }

    private suspend fun Flow<Int>.join(): String {
        val sb = StringBuilder()

        collect {
            sb.append(it).append(", ")
        }

        return sb.toString()
    }
}