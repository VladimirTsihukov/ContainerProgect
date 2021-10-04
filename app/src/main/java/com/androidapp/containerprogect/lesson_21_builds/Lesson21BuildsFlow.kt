package com.androidapp.containerprogect.lesson_21_builds

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class Lesson21BuildsFlow {

    fun createFlow() = flow<String> {
        emit("a")
        emit("b")
        emit("c")
    }

    fun createAsFlow() = listOf(1, 2, 3).asFlow().map { it * 2 }

    suspend fun createFlowTerminalFlow() = flowOf("22", "33", "44").count()

    fun createFlowSynchronous() = flow {
        delay(1000)
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
    }

    fun createAsynchronous() = flow {
        coroutineScope {

            val channel = produce {
                launch {
                    delay(1000)
                    send(1)
                }
                launch {
                    delay(1000)
                    send(2)
                }
                launch {
                    delay(1000)
                    send(3)
                }
            }
            channel.consumeEach {
                emit(it)
            }
        }
    }

    fun createAsynchronousChannelFlow() = channelFlow {
        launch {
            delay(1000)
            send(1)
        }
        launch {
            delay(1000)
            send(2)
        }
        launch {
            delay(1000)
            send(3)
        }
    }
}