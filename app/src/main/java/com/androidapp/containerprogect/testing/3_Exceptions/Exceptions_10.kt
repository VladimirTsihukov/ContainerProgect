package com.androidapp.containerprogect.testing.`3_Exceptions`

import com.androidapp.containerprogect.testing.candy
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught $throwable")
    }

    val exceptionHandler2 = CoroutineExceptionHandler { _, throwable ->
        println("Error $throwable")
    }
    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

   val job = scope.launch {

        coroutineScope {
            val child1 = scope.launch {
                println("A")
                delay(500)
                println("B")
                throw Exception()
            }

            val child2 = scope.launch {
                println("C")
                delay(1_000)
                candy
            }
            
            joinAll(child1, child2)
        }
    }

    job.join()

    println("D")
}
