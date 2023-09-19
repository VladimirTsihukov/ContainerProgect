package com.androidapp.containerprogect.testing.actor

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val counter = counterActor() // Создаем актора

    // Запускаем несколько корутин, которые отправляют сообщения актору
    val jobs = List(100) {
        launch {
            repeat(1000) {
                counter.send(CounterMsg.Increment)
            }
        }
    }

    // Дожидаемся завершения всех корутин
    jobs.forEach { it.join() }

    // Получаем текущее значение счетчика
    val response = CompletableDeferred<Int>()
    counter.send(CounterMsg.GetValue(response))
    println("Значение счетчика: ${response.await()}") // Ожидаемый результат: 100_000

    counter.close() // Закрываем актора
}


@OptIn(ObsoleteCoroutinesApi::class)
fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0 // Изолированное состояние актора

    for (msg in channel) {
        when (msg) {
            is CounterMsg.Increment -> counter++
            is CounterMsg.GetValue -> msg.response.complete(counter)
        }
    }
}

// Базовый класс для сообщений
sealed class CounterMsg {
    // Сообщение для инкремента счетчика
    data object Increment : CounterMsg()

    // Сообщение для получения текущего значения счетчика
    class GetValue(val response: CompletableDeferred<Int>) : CounterMsg()
}
