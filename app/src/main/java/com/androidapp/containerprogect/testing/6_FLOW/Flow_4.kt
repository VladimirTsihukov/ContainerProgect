package com.androidapp.containerprogect.testing.`6_FLOW`

import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val time = now()
    var result = ""

    stringFlow().flatMapMerge { value ->
        flow {
            withContext(Dispatchers.IO) {
                delay(100)
                emit(value)
            }
        }
    }.collect {
        result += it
    }

    println("result: $result, time: ${time.passed}")
}

private fun stringFlow() = flow {
    ('A'..'E').forEach {
        emit("$it -> ")
        delay(50)
    }
}



