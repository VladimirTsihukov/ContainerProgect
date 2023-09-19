package com.androidapp.containerprogect.testing.`3_Exceptions`

import com.androidapp.containerprogect.testing.candy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

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

    try {
        joinAll(child1)
    } catch (e: Exception) {
        println("Error")
    }

    joinAll(child2)

    println("D")
}
