package com.androidapp.containerprogect.testing.`9_Race`

import com.androidapp.containerprogect.testing.`1_Scope`.treadsScheduler
import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    val time = now()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.treadsScheduler

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                increment()
            }
        }
    }

    withTimeout(10) {
        joinAll(*jobs.toTypedArray())
    }

    println("Final count: ${counter.pretty} in $${time.passed}")
}

private fun increment() {
    counter.count++
}

