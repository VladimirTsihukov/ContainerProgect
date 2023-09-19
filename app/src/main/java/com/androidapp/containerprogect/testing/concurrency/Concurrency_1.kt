package com.androidapp.containerprogect.testing.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/*fun main() = runBlocking {
    val mutexA = Mutex()
    val mutexB = Mutex()

    val job1 = launch {
        mutexA.withLock {
            println("Корутина 1 захватила mutexA")
            delay(100) // Имитация работы
            mutexB.withLock {
                println("Корутина 1 захватила mutexB")
            }
        }
    }

    val job2 = launch {
        mutexB.withLock {
            println("Корутина 2 захватила mutexB")
            delay(100) // Имитация работы
            mutexA.withLock {
                println("Корутина 2 захватила mutexA")
            }
        }
    }

    joinAll(job1, job2)
    println("Завершено")
}*/

fun main() = runBlocking {
    val mutexA = Mutex()
    val mutexB = Mutex()

    val job1 = launch {
        mutexA.withLock {
            println("Корутина 1 захватила mutexA")
            delay(100)
            mutexB.withLock {
                println("Корутина 1 захватила mutexB")
            }
        }
    }

    val job2 = launch {
        mutexA.withLock {
            println("Корутина 2 захватила mutexA")
            delay(100)
            mutexB.withLock {
                println("Корутина 2 захватила mutexB")
            }
        }
    }

    joinAll(job1, job2)
    println("Завершено")
}

