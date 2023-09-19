package com.androidapp.containerprogect.testing.`6_FLOW`

import android.os.Build
import androidx.annotation.RequiresApi
import com.androidapp.containerprogect.testing.now
import com.androidapp.containerprogect.testing.passed
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
fun main() = runBlocking {
    val sharedFlow = stringFlow().shareIn(this, SharingStarted.Lazily, 0)

    launch {
        sharedFlow.collect {
            println("launch_1 $it")
        }
    }

    launch {
        sharedFlow
            .timed(1000)
            .collect {
                println("launch_2 $it")
            }
    }

    Unit
}

private fun stringFlow() = flow {
    ('A'..'E').forEach {
        emit("$it -> ")
        delay(50)
    }
}

private fun Flow<String>.timed(delay: Long): Flow<String> = flow {
    var time: Long = 0

    buffer(5, BufferOverflow.DROP_OLDEST)
        .collect { item ->
            if (time == 0L) {
                time = now()
            }

            emit("passed: ${time.passed}, item: $item")
            delay(delay)
        }
}



