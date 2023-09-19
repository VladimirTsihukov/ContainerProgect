package com.androidapp.containerprogect.testing.`1_Scope`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val scope1 = CoroutineScope(Job())

    val job1 = scope1.launch {
        println(1)
        
        try {
            coroutineScope {
                delay(500)
                throw Exception()
            }
        } catch (e: Exception) {
            println("2. Error catch")
        }

        coroutineScope {
            delay(500)
            println(3)
        }

        println(4)
    }

    job1.join()
}

