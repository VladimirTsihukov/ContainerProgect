package com.androidapp.containerprogect.exeption

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class TestCoroutineException {

    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val scopeException = CoroutineScope(
        Job()
                + Dispatchers.Default
                + CoroutineExceptionHandler { coroutineContext, throwable ->
            log("CoroutineExceptionHandler - ${throwable.message}," +
                    " name - ${coroutineContext[CoroutineName]?.name}")
        })

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        log("Exception was handled in Coroutine ${coroutineContext[CoroutineName]?.name}")
    }

    private val scope2 = CoroutineScope(SupervisorJob() + Dispatchers.IO + handler)

    fun onRunExample1() {
        log("onRun start")

        scope.launch {
            try {
                Integer.parseInt("a")
            } catch (e: Exception) {
                log("Exception - ${e.message}")
            }
        }

        log("onRun end")
    }

    fun onRunExample2() {
        log("onRun start")

        scopeException.launch {
            Integer.parseInt("a")
        }

        log("onRun end")
    }

    fun onRunExample3() {
        scope2.launch() {
            TimeUnit.MILLISECONDS.sleep(1000)
            Integer.parseInt("w")
        }

        scope2.launch {
            repeat(5) {
                TimeUnit.MILLISECONDS.sleep(300)
                log("second coroutine isActive $isActive")
            }
        }
    }

    fun onRunExample4() {
        scope2.launch(CoroutineName("1")) {

            launch(CoroutineName("1_1")) {
                TimeUnit.MILLISECONDS.sleep(1000)
                log("EXCEPTION")
                Integer.parseInt("A")
            }

            launch(CoroutineName("1_2")) { repeatIsActive() }

            repeatIsActive()
        }

        scope2.launch(CoroutineName("2")) {

            launch(CoroutineName("2_1")) { repeatIsActive() }

            launch(CoroutineName("2_2")) { repeatIsActive() }

            repeatIsActive()
        }
    }

    fun onRunExample5() {
        scopeException.launch(CoroutineName("Launch")) {

            val deferred =
                async(SupervisorJob(coroutineContext[Job]) + CoroutineName("Async")) {
                Integer.parseInt("W")
                "Result"
            }

            log("Before result")
            val result = deferred.await()
            log(result)
        }
    }

    private fun CoroutineScope.repeatIsActive() {
        repeat(5) {
            TimeUnit.MILLISECONDS.sleep(300)
            log("Coroutine_${coroutineContext[CoroutineName]?.name} isActive $isActive")
        }
    }
}