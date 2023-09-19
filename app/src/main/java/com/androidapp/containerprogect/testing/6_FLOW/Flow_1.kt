package com.androidapp.containerprogect.testing.`6_FLOW`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    requestFlow()
        .map { performRequest(it) }
        .catch { println(it.message) }
        .collect {
            println(it)
        }
}

private fun requestFlow() = flow {
    for (i in 1..3) {
        emit(i)
    }
}

private suspend fun performRequest(request: Int): String {
    delay(100)
    if (request == 2) throw Exception("Error")
    return "$request request"
}
