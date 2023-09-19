package com.androidapp.containerprogect.testing.`3_Exceptions`

import com.androidapp.containerprogect.testing.candy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

    val child1 = scope.async {
        println("A")
        delay(500)
        println("B")
        throw Exception()
    }

    val child2 = scope.async {
        println("C")
        delay(1_000)
        candy
    }

    try {
        child1.await()
    } catch (e: Exception) {
        println("Error")
    }

    child2.await()

    println("D")
}
