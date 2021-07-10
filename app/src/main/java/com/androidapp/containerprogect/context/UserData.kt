package com.androidapp.containerprogect.context

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class UserData(
    private val id: Long,
    private val name: String,
    private val age: Int,
) : AbstractCoroutineContextElement(UserData) {
    companion object Key: CoroutineContext.Key<UserData>

    override fun toString(): String {
        return "id - $id; name - $name; age - $age"
    }
}