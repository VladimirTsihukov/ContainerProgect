package com.androidapp.containerprogect.testing.`4_Timeout`

import com.androidapp.containerprogect.testing.success
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(5) { i ->
            println(i)
            delay(500)
        }
    }

    println(success)
}
