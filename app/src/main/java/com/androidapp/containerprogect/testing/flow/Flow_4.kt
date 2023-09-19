package com.androidapp.containerprogect.testing.flow

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Создаем Flow, который эмитирует значения от 1 до 5 с задержкой в 1 секунду
    val flow = (1..5).asFlow()
        .onEach { delay(1000) }

    // Преобразуем Flow в ReceiveChannel
    val channel: ReceiveChannel<Int> = flow.produceIn(this)

    // Чтение значений из ReceiveChannel
    channel.consumeEach {
        println("Получено значение: $it")
    }

    println("Работа завершена")
}
