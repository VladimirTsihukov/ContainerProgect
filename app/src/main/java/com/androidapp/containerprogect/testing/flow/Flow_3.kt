package com.androidapp.containerprogect.testing.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
            println("Emitted $i on thread ${Thread.currentThread().name}")
        }
    }.buffer() // Добавляем буферизацию

    flow.collect { value ->
        delay(300)
        println("Collected $value on thread ${Thread.currentThread().name}")
    }
}
