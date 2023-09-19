package com.androidapp.containerprogect.testing.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow = flow {
        println("Started flow on thread ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }.flowOn(Dispatchers.IO) // Переключаем контекст на IO-диспетчер

    flow.collect { value ->
        println("Collected $value on thread ${Thread.currentThread().name}")
    }
}
