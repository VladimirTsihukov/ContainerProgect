package com.androidapp.containerprogect.testing.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(Job())

    val job = scope.launch {

        flow {
            coroutineScope {
                val channel = produce {
                    launch {
                        delay(300)
                        send(1)
                    }
                    launch {
                        delay(600)
                        send(2)
                    }
                    launch {
                        delay(1_000)
                        send(3)
                    }
                }

                channel.consumeEach {
                    emit(it)
                }
            }
        }.collect {
            println("emit: $it")
        }
    }

    job.join()
}
