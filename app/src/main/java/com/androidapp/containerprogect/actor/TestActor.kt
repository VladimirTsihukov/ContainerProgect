package com.androidapp.containerprogect.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.containerprogect.log
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay

class TestActor: ViewModel() {
    private val actorChannel = viewModelScope.actor<Unit> {
        for (click in channel) {
            delay(1000)
            val data = "Data"
            log("data = $data")
        }
    }

    fun onButtonClick() {
        log("Click")
        actorChannel.offer(Unit)
    }
}

