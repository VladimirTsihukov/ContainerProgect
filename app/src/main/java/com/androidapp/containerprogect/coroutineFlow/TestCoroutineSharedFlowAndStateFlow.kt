package com.androidapp.containerprogect.coroutineFlow

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class TestCoroutineSharedFlowAndStateFlow {

    private val _eventBus = MutableSharedFlow<String>(
        replay = 3,
        extraBufferCapacity = 2,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    private val eventBus = _eventBus.asSharedFlow()
    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        log("Error - ${throwable.message}, nameCoroutine - ${coroutineContext[CoroutineName]?.name}")
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main + handler)

    fun testSharedFlow() {
        scope.launch {
            for (i in 1..5) {
                delay(500)
                _eventBus.emit(i.toString())
                log("Emit - $i")
            }
        }

        scope.launch {
            delay(2500)
            eventBus.collect {
                //delay(2000)
                log("Collect - $it")
            }
        }
    }

    fun subscriptionCount() {
        _eventBus.subscriptionCount
                .map { count -> count > 0 } // map count into active/inactive flag
                .distinctUntilChanged() // only react to true<->false changes
                .onEach { isActive -> // configure an action
                    if (isActive) log("SubscriptionCount - True") else log("SubscriptionCount - False")
                }
                .launchIn(scope) // launch it
    }

    fun differenceStateFlow() {
        val _progress = MutableStateFlow<Int>(0)
        val progress = _progress.asStateFlow()
        scope.launch {
            for (i in 1..5) {
                delay(300)
                _progress.value = 1
            }
        }
        scope.launch {
            progress.collect {
                log("StateFlow - $it")
            }
        }
    }
}