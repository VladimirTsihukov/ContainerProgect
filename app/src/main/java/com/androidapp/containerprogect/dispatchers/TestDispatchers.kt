package com.androidapp.containerprogect.dispatchers

import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TestDispatchers {

    private val scopeDispatchers = CoroutineScope(Dispatchers.Default)
    private val scopeIO = CoroutineScope(Dispatchers.IO)
    private val scopeExecutor =
        CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    private val scopeMain = CoroutineScope(Dispatchers.Main)
    private val scopeUnconfined = CoroutineScope(Dispatchers.Unconfined)

    fun dispatcherDefault() {
        repeat(6) {
            scopeDispatchers.launch {
                log("coroutine $it, start")
                TimeUnit.MILLISECONDS.sleep(3000)
                log("coroutine $it, end")
            }
        }
    }

    fun dispatcherDefault2() {
        scopeDispatchers.launch {
            log("START coroutine")
            val data = getData()
            log("END coroutine")
        }
    }

    fun dispatcherUnconfined() {
        scopeUnconfined.launch {
            log("START coroutine")
            val data = getData()
            log("END coroutine")
        }
    }

    fun dispatcherIO() {
        repeat(6) {
            scopeIO.launch {
                log("coroutine $it, start")
                TimeUnit.MILLISECONDS.sleep(3000)
                log("coroutine $it, end")
            }
        }
    }

    fun dispatcherExecutor() {
        repeat(6) {
            scopeExecutor.launch {
                log("coroutine $it, start")
                TimeUnit.MILLISECONDS.sleep(3000)
                log("coroutine $it, end")
            }
        }
    }

    fun dispatcherMain() {
        scopeMain.launch {
            val data = getData()
            log(data)
        }
    }

    private suspend fun getData(): String =
        suspendCoroutine {
            log("suspend function - start")
            thread {
                log("suspend function, background work")
                TimeUnit.MILLISECONDS.sleep(1000)
                it.resume("DATA!")
            }
        }
}