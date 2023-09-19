package com.androidapp.containerprogect.testing.`9_Race`

import com.androidapp.containerprogect.testing.`1_Scope`.treadsScheduler
import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    val time = now()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.treadsScheduler

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                mutex.withLock {
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

private val mutex = Mutex()

private suspend fun increment() {
    delay(Random.nextLong(0, 2))
    counter.count++
}



