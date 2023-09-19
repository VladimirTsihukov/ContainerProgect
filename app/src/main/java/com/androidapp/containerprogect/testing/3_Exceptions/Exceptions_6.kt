package com.androidapp.containerprogect.testing.`3_Exceptions`

import com.androidapp.containerprogect.testing.bomb
import com.androidapp.containerprogect.testing.candy
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val exceptionHandler =
        CoroutineExceptionHandler { _, _ -> println(bomb) }
    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

        val child1 = scope.launch {
            println("A")
            delay(500)
            println("B")
            throw Exception()
        }

        val child2 = scope.launch {
            println("C")
            delay(1_000)
            println(candy)
        }

        joinAll(child1, child2)
}
