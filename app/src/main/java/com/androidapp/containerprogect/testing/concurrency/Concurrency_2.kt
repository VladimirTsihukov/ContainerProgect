package com.androidapp.containerprogect.testing.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*fun main() = runBlocking {
    val list = mutableListOf<Int>()
    val flow = MutableSharedFlow<Int>()

    launch {
        flow.collect {
            println(it)
            list.add(it)
        }
    }

    val job1 = launch(Dispatchers.Default) {
        repeat(100) {
            flow.emit(it)
        }
    }

    val job2 = launch(Dispatchers.Default) {
        repeat(100) {
            flow.emit(it)
        }
    }

    joinAll(job1, job2)

    println("size: ${list.size}")
}*/

fun main() = runBlocking {
    val list = mutableListOf<String>()
    val channel = Channel<String>()

    launch {
        channel.consumeEach {
            println(it)
            list.add(it)
        }
    }

    val job1 = launch(Dispatchers.Default) {
        repeat(100) {
            channel.send("job1 -> $it")
        }
    }

    val job2 = launch(Dispatchers.Default) {
        repeat(100) {
            channel.send("job2 -> $it")
        }
    }

    joinAll(job1, job2)

    println("size: ${list.size}")
}


