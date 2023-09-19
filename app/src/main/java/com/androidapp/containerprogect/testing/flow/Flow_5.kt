package com.androidapp.containerprogect.testing.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow = flow {
        for (i in 0..5) {
            try {
                delay(500)
                if (i == 3) throw Exception("Error")
                emit(i)
            } catch (e: Exception) {
                println("Error")
            }
        }
    }

    flow
        //.catch { println(it.message) }
        .collect {
            println(it)
        }
}

