package com.androidapp.containerprogect.testing.`9_Race`

import com.androidapp.containerprogect.testing.`1_Scope`.treadsScheduler
import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random.Default.nextLong

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

    joinAll(*jobs.toTypedArray())

    println("Final count: ${counter.pretty} in $${time.passed}")
}

private suspend fun increment() {
    val oldValue = counter.count
    val newValue = oldValue + 1
    delay(nextLong(0, 2))
    counter.count = newValue
}
