package com.androidapp.containerprogect

import java.io.Serializable

class PersonSerializable(
    private val name: String,
    private val surName: String,
    private val age: Int,
) : Serializable