package com.androidapp.containerprogect.testing.`3_Exceptions`

import com.androidapp.containerprogect.testing.bomb
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val scope = CoroutineScope(Job())

    val job1 = scope.launch {

        val child1 = launch {
            println("A")
            delay(500)
            println("B")
            try {
                throw RuntimeException()
            } catch (e: Exception) {
                println("Error")
            }
        }

        val child2 = launch {
            println("C")
            delay(1_000)
            println("Finish")
        }

        joinAll(child1, child2)
    }

    val job2 = scope.launch {
        delay(2_000)
        println("D")
    }

    joinAll(job1, job2)
}

fun main2() = runBlocking {
    val exceptionHandler =
        CoroutineExceptionHandler { _, _ -> println(bomb) }
    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

    val job1 = scope.launch {

        val child1 = launch {
            println("A")
            delay(500)
            println("B")
            throw RuntimeException()
        }

        val child2 = launch {
            println("C")
            delay(1_000)
            println("Finish")
        }

        joinAll(child1, child2)
    }

    val job2 = scope.launch {
        delay(2_000)
        println("D")
    }

    joinAll(job1, job2)
}

