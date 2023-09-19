package com.androidapp.containerprogect.testing.`7_TimeDuration`

import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val time = now()

    val job1 = launch {
        println(1)
        delay(1_000)
        println(2)
    }

    val job2 = launch {
        println(3)
        Thread.sleep(1_000)
        println(4)
    }

    val job3 = launch {
        println(5)
        delay(2_000)
        println(6)
    }

    joinAll(job1, job2, job3)

    println("time: ${time.passed}")
}

/*
1
3
5
2
4
6
time: 2012
*/

