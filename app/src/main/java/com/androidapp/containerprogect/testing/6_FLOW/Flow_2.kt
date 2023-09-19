package com.androidapp.containerprogect.testing.`6_FLOW`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

fun main() = runBlocking {
    withTimeoutOrNull(250) {
        numberFlow()
            .collect {
                delay(50)
                println(it)
            }
    }

    Unit
}

private fun numberFlow() = flow {
    repeat(3) {
        delay(100)
        emit(it)
    }
}



