package com.androidapp.containerprogect.concurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.containerprogect.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

class TestConcurrency : ViewModel() {
    private var j = 0
    private val mutex = Mutex()

    private val singleThreadDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val actorChannel = viewModelScope.actor<Unit> {
        for (e in channel) {
            j++
        }
    }

    fun test1() {
        viewModelScope.launch() {
            var i = 0

            val job1 = launch(Dispatchers.Default) {
                repeat(10000) {
                    i++
                }
            }

            val job2 = launch(Dispatchers.Default) {
                repeat(10000) {
                    i--
                }
            }

            job1.join()
            job2.join()

            log("Result i = $i")
        }
    }

    fun test2() {
        viewModelScope.launch() {
            var i = 0

            val job1 = launch(singleThreadDispatcher) {
                repeat(10000) {
                    i++
                }
            }

            val job2 = launch(singleThreadDispatcher) {
                repeat(10000) {
                    i--
                }
            }

            job1.join()
            job2.join()
            log("Result i = $i")
        }
    }

    fun test3() {
        viewModelScope.launch() {
            var i = AtomicInteger()

            val job1 = launch(Dispatchers.Default) {
                repeat(10000) {
                    i.incrementAndGet()
                }
            }
            val job2 = launch(Dispatchers.Default) {
                repeat(10000) {
                    i.decrementAndGet()
                }
            }

            job1.join()
            job2.join()

            log("Result i = $i")
        }
    }

    fun test4() {
        viewModelScope.launch() {

            val job1 = launch(Dispatchers.Default) {
                repeat(1000) {
                    delay(1)
                    increment()
                }
            }
            val job2 = launch(Dispatchers.Default) {
                repeat(1000) {
                    delay(1)
                    increment()
                }
            }

            job1.join()
            job2.join()

            log("Result j = $j")
        }
    }

    fun test5() {
        viewModelScope.launch() {

            val job1 = launch(Dispatchers.Default) {
                repeat(10000) {
                    incrementMutex()
                }
            }
            val job2 = launch(Dispatchers.Default) {
                repeat(10000) {
                    incrementMutex()
                }
            }

            job1.join()
            job2.join()

            log("Result j = $j")
        }
    }

    fun test6() {
        viewModelScope.launch() {

            val job1 = launch(Dispatchers.Default) {
                repeat(10000) {
                    actorChannel.send(Unit)
                }
            }
            val job2 = launch(Dispatchers.Default) {
                repeat(10000) {
                    actorChannel.send(Unit)
                }
            }

            job1.join()
            job2.join()

            log("Result j = $j")
        }
    }

    private suspend fun incrementMutex() {
        mutex.withLock {
            j++
        }
    }

    @Synchronized
    private fun increment() {
        j++
    }

}