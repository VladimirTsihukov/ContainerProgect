package com.androidapp.containerprogect.testing.`9_Race`

import com.androidapp.containerprogect.testing.`1_Scope`.treadsScheduler
import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    val time = now()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.treadsScheduler

    repeat(1_000) {
        synchronized(this) {
            jobs += launch(customDispatcher) {
                repeat(1_000) {
                    increment()
                }
            }
        }
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }

    println("Final count: ${counter.pretty} in ${time.passed}ms")
}

private fun increment() {
    counter.count++
}

