package com.androidapp.containerprogect.testing.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flow {
        //delay(1_000)
        emit("A")
        delay(1_000)
        emit("B")
        delay(1_000)
        emit("C")
    }

    flow1.combine(flow2) { numver, letter ->
        "$numver) $letter"
    }
        .collect {
            println(it)
        }

}
