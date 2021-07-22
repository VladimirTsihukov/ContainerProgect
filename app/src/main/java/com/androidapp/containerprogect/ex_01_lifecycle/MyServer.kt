package com.androidapp.containerprogect.ex_01_lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.androidapp.containerprogect.getLog

class MyServer : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun connect() = getLog("Connect server")

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun disconnect() = getLog("Disconnect server")

}