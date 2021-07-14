package com.androidapp.containerprogect.flow

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*

class TestFlow {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    suspend fun testFlow1() {
        val flowTest = flow {
            for (i in 1..10) {
                delay(500)
                emit(i)
            }
        }

        log("Delay 1 sec")
        delay(1000)

        flowTest.collect {
            log("Flow - $it")
        }

    }

    fun createFlow(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(500)
            emit(i)
        }
    }

    fun collectFlow(flow: Flow<Int>) {
        scope.launch {
            flow.collect {
                log("Collect Flow - $it")
            }
        }
    }

    fun buildFlow1() {
        val listFlow = listOf("a", "b", "c").asFlow()
        val flow = flowOf("c", "d", "y")

        scope.launch {
            listFlow.collect {
                delay(200)
                log("asFlow - $it")
            }

            flow.collect {
                delay(200)
                log("flowOf - $it")
            }
        }
    }

    fun operatorsIntermediateFlow() {
        val flowMap = flowOf(1, 2, 3).map { it * 10 }
        scope.launch {
            flowMap.collect {
                delay(300)
                log("flowMap - $it")
            }
        }
    }

    fun operatorTerminalFlow() {
        scope.launch {
            val count = flowOf("a", "b", "c").count()
            log("Flow count - $count")
        }
    }

    fun myTerminalFlow() {
        val flowString = flow {
            emit("abc")
            emit("def")
            emit("fun")
        }

        scope.launch {
            log(flowString.join())
        }

    }

    fun myIntermediateFlow() {
        val flowString = flow {
            emit("abc")
            emit("def")
            emit("fun")
        }

        scope.launch {
            flowString.toUpperCase().collect {
                log(it)
            }
        }
    }

    fun testFlow() = flow {
        coroutineScope {
            launch {
                delay(1000)
                emit(1)
            }

            launch {
                delay(1000)
                emit(2)
            }

            launch {
                delay(1000)
                emit(3)
            }
        }
    }

    fun createMyChannelFlow() = flow<Int> {
        coroutineScope {
            val channel = produce<Int> {
                log("Start channel")
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

    fun channelFlow() = channelFlow<Int> {
        log("Start channelFlow")
        launch {
            delay(1000)
            send(10)
        }
        launch {
            delay(1000)
            send(11)
        }
        launch {
            delay(1000)
            send(12)
        }
    }

    fun testFlowOn() {
        scope.launch {
            flow {
                for (i in 1..5) {
                    log("Emit $i")
                    emit(i)
                }
            }.map { it * 10 }
                .flowOn(Dispatchers.IO)
                .onEach {
                    delay(500)
                    log("onEach $it")
                }
                .flowOn(Dispatchers.Main)
                .collect()
        }
    }

    fun testProduceIn() = flow {
            for (i in 1..20) {
                emit(i)
            }
        }
            .buffer(5)
            .flowOn(Dispatchers.IO)
            .produceIn(scope)

}


fun Flow<String>.toUpperCase(): Flow<String> = flow {
    collect {
        delay(300)
        emit(it.uppercase())
    }
}

suspend fun Flow<String>.join(): String {
    val sb = StringBuilder()
    collect {
        sb.append(it).append(", ")
    }
    return sb.toString()
}