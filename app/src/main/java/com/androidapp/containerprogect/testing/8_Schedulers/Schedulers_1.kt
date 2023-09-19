package com.androidapp.containerprogect.testing.`8_Schedulers`

import com.androidapp.containerprogect.testing.`1_Scope`.treadsScheduler
import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val customScheduler = 1.treadsScheduler
    val time = now()

    val task1 = async(customScheduler) {
        heavyComputation(1)
    }

    val task2 = async(customScheduler) {
        heavyComputation(2)
    }

    val task3 = async(customScheduler) {
        heavyComputation(3)
    }

    joinAll(task1, task2, task3)

    println("Time: ${time.passed}")
}

private suspend fun heavyComputation(task: Int): Int {
    println("Task $task started")
    delay(1_000)
    println("Task $task completed")
    return task
}
