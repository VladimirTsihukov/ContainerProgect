package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import okio.ByteString.Companion.decodeHex

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}

class EchoWebSocketListener: WebSocketListener() {
    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello, it's Saurel !")
        webSocket.send("What's up ?")
        webSocket.send("deadbeef".decodeHex())
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
    }
}