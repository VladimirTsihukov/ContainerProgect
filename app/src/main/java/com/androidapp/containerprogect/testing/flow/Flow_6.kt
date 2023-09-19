package com.androidapp.containerprogect.testing.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow = flow {
        for (i in 0..5) {
            delay(500)
            if (i == 3) throw Exception("Error")
            emit(i)
        }
    }

    flow
        .retry(3)
        .catch { println("Error") }
        .collect {
            println(it)
        }
}

