package com.androidapp.containerprogect.model

import androidx.annotation.DrawableRes
import com.androidapp.containerprogect.adapter.Item

data class UserPost(
    val postId: Long,
    val userNickname: String,
    val text: String,
    val likesCount: Int,
    val commentsCount: Int,
    @DrawableRes
    val imageResId: Int,
    val isSaved: Boolean
) : Item