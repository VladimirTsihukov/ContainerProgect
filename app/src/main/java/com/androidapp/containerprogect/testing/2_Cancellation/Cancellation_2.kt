package com.androidapp.containerprogect.testing.`2_Cancellation`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val child = launch {
            try {
                println("A")
                delay(Long.MAX_VALUE)
            } finally {
                println("B")
            }
        }

        delay(1_000)
        println("C")
        supervisor.cancel()
    }

    println("D")
}
