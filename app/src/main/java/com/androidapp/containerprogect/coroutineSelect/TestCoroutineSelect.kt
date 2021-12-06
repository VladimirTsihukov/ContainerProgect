package com.androidapp.containerprogect.coroutineSelect

import com.androidapp.containerprogect.log
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

class TestCoroutineSelect {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun testSelect() {
        scope.launch {
            val async1 = async {
                delay(2000)
                "async 1"
            }
            val async2 = async {
                delay(1000)
                "async 2"
            }

            val result = select<String> {
                //1
                async1.onAwait {
                    it
                }

                //2
                async2.onAwait {
                    //4
                    it
                }

                onTimeout(3000) {
                    "Time out"
                }

                //3
            }
            //5
            log(result)
        }
    }
}