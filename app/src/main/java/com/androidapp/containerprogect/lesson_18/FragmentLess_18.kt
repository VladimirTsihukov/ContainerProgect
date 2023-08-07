package com.androidapp.containerprogect.lesson_18

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentLess_18 : Fragment(R.layout.fragment_less_9) {

    private val channel = Channel<Int>()
    private val scope = CoroutineScope(Dispatchers.Default)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        function_4()
    }

    private fun function_1() {
        scope.launch {

            launch {
                delay(300)
                log("Send 5")
                channel.send(5)
                log("Send done")
            }

            launch {
                delay(1_000)
                log("Receive")
                val i = channel.receive()
                log("Receive $i, done")
            }
        }
    }

    private fun function_2() {
        scope.launch {

            launch {
                delay(1_000)
                log("Send 5")
                channel.send(5)
                log("Send done")
            }

            launch {
                delay(300)
                log("Receive")
                val i = channel.receive()
                log("Receive $i, done")
            }
        }
    }

    private fun function_3() {
        scope.launch {

            launch {
                log("Send 1")
                channel.send(1)
                log("Send 2")
                channel.send(2)
                log("Send 3")
                channel.send(3)
            }

            launch {
                log("Receive №${channel.receive()}, done")
                log("Receive №${channel.receive()}, done")
            }
        }
    }

    private fun function_4() {
        scope.launch {

            launch {
                repeat(5) {
                    log("Send $it")
                    channel.send(it)
                }
            }

            launch {
                for (element in channel) {
                    log("Receive №$element, done")
                }
            }
        }
    }

    private fun function_5() {
        scope.launch {

        }
    }
}