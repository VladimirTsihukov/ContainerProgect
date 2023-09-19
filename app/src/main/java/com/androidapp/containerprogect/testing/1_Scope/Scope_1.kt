package com.androidapp.containerprogect.testing.`1_Scope`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val jobA = launch {
        delay(500)
        println("A")
    }

    val jobB = launch {
        delay(1_000)
        println("B")
    }

    println("C")
}
