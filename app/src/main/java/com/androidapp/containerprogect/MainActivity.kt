package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.decodeHex

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            startWebSocket()
        }
    }

    private fun startWebSocket() {
        val client = OkHttpClient()
        val request = Request.Builder().url("wss://echo.websocket.org").build()
        val listener = EchoWebSocketListener()
        val webSocket = client.newWebSocket(request, listener)
        client.dispatcher.executorService.shutdown()
    }

    private fun output(text: String) {
        runOnUiThread {
            outputText.text = ("${outputText.text} \n\n $text")
        }
    }

    inner class EchoWebSocketListener : WebSocketListener() {

        val NORMAL_CLOSURE_STATUS = 1892

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Log.i(TAG, "onOpen()")
            webSocket.send("Hello, it's Saurel!")
            webSocket.send("Hello, it's Saurel!")
            webSocket.send("What's up ?")
            webSocket.send("deadbeef".decodeHex())
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye!")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            Log.i(TAG, "onMessage()")
            output("Receiving: $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            Log.i(TAG, "onMessage()")
            output("Receiving byte: ${bytes.hex()}")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            Log.i(TAG, "onClosed()")
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("onClosed: $code - $reason")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            Log.i(TAG, "onClosing()")
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing: $code - $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            Log.i(TAG, "onFailure(): message - ${t.message}")
            output("Error: ${t.message}")
        }
    }
}
