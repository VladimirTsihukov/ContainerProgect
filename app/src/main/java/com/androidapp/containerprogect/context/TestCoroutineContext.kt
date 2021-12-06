package com.androidapp.containerprogect.context

import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class TestCoroutineContext {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun main() {
        log("scope - ${contextToString(scope.coroutineContext)}")

        scope.launch {
            log("coroutine level 1 - ${contextToString(coroutineContext)}")

            scope.launch(Dispatchers.IO) {
                log("coroutine level 2 - ${contextToString(coroutineContext)}")

                scope.launch {
                    log("coroutine level 3 - ${contextToString(coroutineContext)}")
                }
            }
        }
    }

    private fun contextToString(context: CoroutineContext): String =
        "Job = ${context[Job]}, Dispatcher = ${context[ContinuationInterceptor]}"
}