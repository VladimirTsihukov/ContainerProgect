package com.androidapp.containerprogect.model

import android.os.Parcelable
import com.androidapp.containerprogect.adapter.Item

data class HorizontalItem(
    val items: List<Item>,
    var state: Parcelable? = null
) : Item {
}