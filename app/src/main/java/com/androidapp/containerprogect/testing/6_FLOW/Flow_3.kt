package com.androidapp.containerprogect.testing.`6_FLOW`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val time = System.currentTimeMillis()
    var result = ""

    stringFlow()
        .flowOn(Dispatchers.IO) //добавляет буфер из-за этого мы не будем учитывать 500мс в map
        .map { item ->
            delay(500)
            item
        }
        .collect {
            result += it
        }

    println("result: $result, time: ${System.currentTimeMillis() - time}")
}

private fun stringFlow() = flow {
    ('A'..'E').forEach {
        emit("$it -> ")
        delay(1_000)
    }
}



