package com.androidapp.containerprogect.testing.`1_Scope`

import com.androidapp.containerprogect.testing.threadName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    val context1 = 1.treadsScheduler
    val context2 = 2.treadsScheduler

    launch(context1) {
        println("A: $threadName")

        withContext(context2) {
            delay(500)
            println("B: $threadName")
        }

        println("C: $threadName")
    }

    Unit
}

val Int.treadsScheduler
    get() = this.treadsScheduler("Threads - $this")

@OptIn(DelicateCoroutinesApi::class)
fun Int.treadsScheduler(name: String) = newFixedThreadPoolContext(
    nThreads = this,
    name = name
)
