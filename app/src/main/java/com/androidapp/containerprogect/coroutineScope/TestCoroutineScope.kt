package com.androidapp.containerprogect.coroutineScope

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*

class TestCoroutineScope {

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        log("Exception: ${throwable.message}, in Coroutine - ${coroutineContext[CoroutineName]?.name}")
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO + handler)

    fun onRun() {
        scope.launch(CoroutineName("1")) {

            launch(CoroutineName("1_1")) {
                log("Start coroutine 1_1")
                delay(500)
                log("End coroutine 1_1")
            }

            try {
                launch {
                    throwException()
                }
            } catch (e: Exception) {
                log("Exception - ${e.message}")
            }

            launch(CoroutineName("1_3")) {
                log("Start coroutine 1_3")
                delay(1000)
                log("End coroutine 1_3")
            }

            launch(CoroutineName("1_4")) {
                log("Start coroutine 1_4")
                delay(1500)
                log("End coroutine 1_4")
            }
        }

        scope.launch(CoroutineName("2")) {
            delay(300)
            launch(CoroutineName("2_1")) {
                log("Start coroutine 2_1")
                delay(2000)
                log("End coroutine 2_1")
            }

            launch(CoroutineName("2_2")) {
                log("Start coroutine 2_2")
                delay(2500)
                log("End coroutine 2_2")
            }
        }
    }

    private suspend fun throwException() {
        coroutineScope {
            launch(CoroutineName("1_2")) {
                log("Start coroutine 1_2")
                delay(800)
                Integer.parseInt("W")
                log("End coroutine 1_2")
            }
        }
    }
}