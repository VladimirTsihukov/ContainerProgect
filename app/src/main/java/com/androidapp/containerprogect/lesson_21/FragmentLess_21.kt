package com.androidapp.containerprogect.lesson_21

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch

class FragmentLess_21 : Fragment(R.layout.fragment_less_9) {

    private val scope = CoroutineScope(Job())

    private val _channelFlow = channelFlow<Int> {
        launch {
            delay(1_000)
            send(1)
        }
        launch {
            delay(1_000)
            send(2)
        }
        launch {
            delay(1_000)
            send(3)
        }
    }

    private val channel = flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }
        .buffer(3)
        .flowOn(Dispatchers.IO)
        .produceIn(scope)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    /*    scope.launch {
            _channelFlow.collect {
                log("Emit $it")
            }
        }*/
        scope.launch {
            channel.consumeEach {
                log("channel - $it")
            }
        }
    }

    private fun myChannelFlow() {
        scope.launch {
            flow {
                coroutineScope {
                    val channel = produce<Int> {
                        launch {
                            delay(1_000)
                            send(1)
                        }
                        launch {
                            delay(1_000)
                            send(2)
                        }
                        launch {
                            delay(1_000)
                            send(3)
                        }
                    }

                    channel.consumeEach {
                        emit(it)
                    }
                }
            }
        }
    }

}