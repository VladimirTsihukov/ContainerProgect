package com.androidapp.containerprogect.channel

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach

@ExperimentalCoroutinesApi
class TestChannel {

    private val channel = Channel<Int>(2) //буфер хранения данных = 2
    private val channelConflated =
        Channel<Int>(Channel.Factory.CONFLATED) // не ждет когда заберут данные, а просто их перезаписывает
    private val channelUnLimited =
        Channel<Int>(Channel.Factory.UNLIMITED) // размер буфер ограничен количеством памяти
    private val channelBuffered = Channel<Int>(Channel.Factory.BUFFERED) // размер буфера = 64

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    fun onRun() {
        scope.launch {
            delay(1000)
            log("channel send - 5")
            channel.send(5)
        }

        scope.launch {
            log("start channel receiver")
            val result = channel.receive()
            log("channel receive - $result")
        }
    }

    fun onRun2() {
        scope.launch {
            delay(300)
            log("1 send 5")
            channel.send(5)
            log("4 send, done")
        }

        scope.launch {
            delay(1000)
            log("2 receiver")
            val result = channel.receive()
            log("3 receiver $result, done")
        }
    }

    fun onRun3() {
        scope.launch {
            delay(1000)
            log("2 send 5")
            channel.send(5)
            log("3 send, done")
        }

        scope.launch {
            delay(300)
            log("1 receiver")
            val result = channel.receive()
            log("4 receiver $result, done")
        }
    }

    fun onRun4() {
        scope.launch {
            channel.send(1)
            delay(1000)
            channel.send(2)
            delay(1000)
/*            channel.send(3)
            delay(1000)
            channel.send(4)
            delay(1000)*/

            channel.close()
        }

        scope.launch {
/*            val result1 = channel.receive()
            log("$result1")

            val result2 = channel.receive()
            log("$result2")

            val result3 = channel.receive()
            log("$result3")*/

            for (element in channel) {
                log("$element")
            }

            log("receiver STOP")
        }
    }

    fun onRun5() {
        scope.launch {
            repeat(5) {
                delay(100)
                log("send $it")
                channel.send(it)
            }
            log("Close")
            channel.close()
        }

        scope.launch {
            for (element in channel) {
                log("received $element")
                delay(500)
            }

            log("receiver STOP")
        }
    }

    fun onRun6() {
        scope.launch {
            coroutineContext[Job]?.invokeOnCompletion {
                channel.close()
            }

            launch {
                channel.send(1)
                delay(500)
                channel.send(2)
                delay(500)
                channel.send(3)
            }

            launch {
                channel.send(22)
                delay(500)
                channel.send(33)
                delay(500)
                channel.send(44)
            }
        }

        scope.launch {
            for (element in channel) {
                log("received $element")
            }

            log("Receiver STOP")
        }
    }

    fun onRun7() {
        scope.launch {

            launch {
                repeat(5) {
                    channel.send(it)
                    delay(500)
                }
            }

            launch {
                channel.consumeEach { element ->
                    log("$element")
                }
            }
        }
    }

    fun onRun8() {
        val broadcastChannel = BroadcastChannel<Int>(1)
        val channel1 = broadcastChannel.openSubscription()
        val channel2 = broadcastChannel.openSubscription()

        scope.launch {
            repeat(5) {
                log("SEND $it")
                broadcastChannel.send(it)
                delay(500)
            }
        }

        scope.launch {
            channel1.consumeEach { element ->
                log("Consume_1 - $element")
            }
        }

        scope.launch {
            channel2.consumeEach { element ->
                log("Consume_2 - $element")
            }
        }
    }
}