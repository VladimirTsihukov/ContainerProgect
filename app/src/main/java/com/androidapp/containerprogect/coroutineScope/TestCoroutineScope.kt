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
                workCoroutine("1_1", 500)
            }

            try {
                launch {
                    throwException()
                }
            } catch (e: Exception) {
                log("Exception - ${e.message}")
            }

            launch(CoroutineName("1_3")) {
                workCoroutine("1_3", 1000)
            }
        }

        scope.launch(CoroutineName("2")) {
            delay(300)
            launch(CoroutineName("2_1")) {
                workCoroutine("2_1", 2000)
            }

            launch(CoroutineName("2_2")) {
                workCoroutine("2_2", 2500)
            }
        }
    }

    fun onRun2() {
        scope.launch(CoroutineName("1")) {

            launch(CoroutineName("1_1")) {
                workCoroutine("1_1", 500)
            }

            try {
                coroutineScope {
                    throwException()
                }
            } catch (e: Exception) {
                log("Exception coroutine 1_2 - ${e.message}")
            }

            launch(CoroutineName("1_3")) {
                workCoroutine("1_3", 1000)
            }
        }

        scope.launch(CoroutineName("2")) {
            delay(300)
            launch(CoroutineName("2_1")) {
                workCoroutine("2_1", 2000)
            }

            launch(CoroutineName("2_2")) {
                workCoroutine("2_2", 2500)
            }
        }
    }

    fun onRun3() {
        scope.launch(CoroutineName("Launch")) {

            launch(CoroutineName("1")) {
                workCoroutine("1", 500)
            }

            supervisorScope {

                launch {
                    workCoroutine("supervisorScope 1", 2000)
                }

                launch {
                    log("Start coroutine supervisorScope 2")
                    delay(800)
                    Integer.parseInt("W")
                    log("End coroutine supervisorScope 2")
                }
            }

            launch(CoroutineName("3")) {
                workCoroutine("3", 1000)
            }
        }

        scope.launch(CoroutineName("4")) {
            delay(300)
            launch(CoroutineName("4")) {
                workCoroutine("4", 2000)
            }
        }
    }

    private suspend fun workCoroutine(name: String, delay: Long) {
        log("Start coroutine $name")
        delay(delay)
        log("End coroutine $name")
    }

    private suspend fun workCoroutine() {
        log("Start coroutine 2_2")
        delay(2500)
        log("End coroutine 2_2")
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