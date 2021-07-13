package com.androidapp.containerprogect.flow

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

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
}