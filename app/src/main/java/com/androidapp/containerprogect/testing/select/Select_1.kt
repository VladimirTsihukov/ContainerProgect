package com.androidapp.containerprogect.testing.select

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select

fun main(): Unit = runBlocking{
    val channel1 = Channel<String>()
    val channel2 = Channel<String>()

    launch {
        delay(500)
        channel1.send("Channel 1")
    }

    launch {
        delay(1_000)
        channel2.send("Channel 2")
    }

    val result = select {
        channel1.onReceive {
            "Получил из $it"
        }

        channel2.onReceive {
            "Получил из $it"
        }

        onTimeout(300) {
            "Данные не поступили за 300мс"
        }
    }

    println(result)

    channel1.close()
    channel2.close()
}
