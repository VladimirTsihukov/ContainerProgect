package com.androidapp.containerprogect.testing.`7_TimeDuration`

import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val time = now()

    runBlocking {
        launch {
            repeat(10) {
                delay(100)
                println("$it, time passed: ${time.passed}")
            }
        }
    }

    println("\nTotal: ${time.passed}")
}


