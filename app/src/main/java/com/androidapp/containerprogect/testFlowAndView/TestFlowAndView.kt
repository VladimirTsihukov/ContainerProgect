package com.androidapp.containerprogect.testFlowAndView

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class TestFlowAndView {

    fun testListenerFlow(view: View) {
        val flow = callbackFlow {
            view.setOnClickListener {
                trySend(Unit)
            }
            awaitClose { view.setOnClickListener(null) }
        }
    }

}
