package com.androidapp.containerprogect.testing.`7_TimeDuration`

import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() {
    val time = now()

    runBlocking {
        repeat(10) {
            launch(Dispatchers.Default) {
           // launch {
                sleep(100)
                println("$it, time passed: ${time.passed}")
            }
        }
    }

    println("\nTotal: ${time.passed}")
}


