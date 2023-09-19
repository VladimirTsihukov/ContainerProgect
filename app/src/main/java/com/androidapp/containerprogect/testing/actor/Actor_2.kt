package com.androidapp.containerprogect.testing.actor

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val bankAccount = bankAccountActor()

    // Корутна для внесения денег
    val depositor = launch {
        repeat(5) {
            bankAccount.send(BankAccountMsg.Deposit(100))
            delay(100)
        }
    }

    // Корутна для снятия денег
    val withdrawer = launch {
        repeat(5) {
            val response = CompletableDeferred<Boolean>()
            bankAccount.send(BankAccountMsg.Withdraw(80, response))
            val success = response.await()
            if (success) {
                println("Снятие успешно")
            } else {
                println("Снятие не удалось")
            }
            delay(150)
        }
    }

    depositor.join()
    withdrawer.join()

    // Получаем итоговый баланс
    val balanceResponse = CompletableDeferred<Int>()
    bankAccount.send(BankAccountMsg.GetBalance(balanceResponse))
    println("Итоговый баланс: ${balanceResponse.await()}")

    bankAccount.close()
}

sealed class BankAccountMsg {
    data class Deposit(val amount: Int) : BankAccountMsg()
    data class Withdraw(val amount: Int, val response: CompletableDeferred<Boolean>) :
        BankAccountMsg()

    class GetBalance(val response: CompletableDeferred<Int>) : BankAccountMsg()
}

@OptIn(ObsoleteCoroutinesApi::class)
fun CoroutineScope.bankAccountActor() = actor<BankAccountMsg> {
    var balance = 0

    for (msg in channel) {
        when (msg) {
            is BankAccountMsg.Deposit -> {
                balance += msg.amount
                println("Депозит: ${msg.amount}, Баланс: $balance")
            }
            is BankAccountMsg.Withdraw -> {
                if (balance >= msg.amount) {
                    balance -= msg.amount
                    msg.response.complete(true)
                    println("Снятие: ${msg.amount}, Баланс: $balance")
                } else {
                    msg.response.complete(false)
                    println("Недостаточно средств для снятия: ${msg.amount}, Баланс: $balance")
                }
            }
            is BankAccountMsg.GetBalance -> msg.response.complete(balance)
        }
    }
}
