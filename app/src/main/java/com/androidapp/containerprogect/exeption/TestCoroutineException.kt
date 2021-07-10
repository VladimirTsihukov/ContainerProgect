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
        log("CoroutineExceptionHandler - ${throwable.message}")
    })

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        log("First coroutine exception ${throwable.message}")
    }

    private val scope2 = CoroutineScope(SupervisorJob() + Dispatchers.Default + handler)

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

}