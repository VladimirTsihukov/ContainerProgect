package com.androidapp.containerprogect.testing.`5_AsyncAwait`

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
fun main() = runBlocking {
    val time = now()

    val deferred1 = async {
        performTask(1)
    }
    val deferred2 = async {
        performTask(2)
    }

    println("A")

    val result1 = deferred1.await()
    val result2 = deferred2.await()

    println("$result1 $result2 ${time.passed}")

}

private fun now(): Long = System.currentTimeMillis()

private val Long.passed: Long
    get() = System.currentTimeMillis() - this

private suspend fun performTask(id: Int): Int {
    delay(1_000)
    return id * 2
}
