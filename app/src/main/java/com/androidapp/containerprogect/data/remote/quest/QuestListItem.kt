package com.androidapp.containerprogect.data.remote.quest

import com.google.gson.annotations.SerializedName

data class QuestListItem(
    val questId: String,

    @SerializedName("questName")
    val name: StringBuilder,

)
