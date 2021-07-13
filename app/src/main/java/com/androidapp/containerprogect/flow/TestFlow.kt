package com.androidapp.containerprogect.flow

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
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

        /*  scope.launch {
              flow {
                  flowString.collect {
                      emit(log(it.uppercase()))
                  }
              }.collect { }
          }*/
    }
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