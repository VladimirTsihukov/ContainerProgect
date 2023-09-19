package com.androidapp.containerprogect.lesson_18

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.MainActivity2
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import com.androidapp.containerprogect.testing.candy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class FragmentLess_18 : Fragment(R.layout.fragment_less_9) {

    private val channel = Channel<Int>()
    private val scope = CoroutineScope(Dispatchers.Default)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        function_1()

        view.findViewById<TextView>(R.id.btn_enter_text).setOnClickListener {
            activity?.application?.let { context ->
                startActivity(Intent(requireContext(), MainActivity2::class.java))
            }
        }

        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            lifecycleScope.launch {
                val child1 = scope.launch {
                    println("A")
                    delay(500)
                    println("B")
                    try {
                        val a = 1 / 0
                    } catch (e: Exception) {
                        println("Error")
                    }
                }

                val child2 = scope.launch {
                    println("C")
                    delay(1_000)
                    candy
                }

                joinAll(child1, child2)

                println("D")
            }
        }
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
                //delay(1_000)
                log("Receive№1")
                val i = channel.receive()
                log("Receive№1 $i, done")
            }

            launch {
                log("Receive№2")
                val i = channel.receive()
                log("Receive№2 $i, done")
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
