package com.androidapp.containerprogect.adapter.fingerprint

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.adapter.BaseViewHolder
import com.androidapp.containerprogect.adapter.Item
import com.androidapp.containerprogect.adapter.ItemFingerprint
import com.androidapp.containerprogect.databinding.ItemPostBinding
import com.androidapp.containerprogect.model.UserPost

class PostFingerprint : ItemFingerprint<ItemPostBinding, UserPost> {

    override fun isRelativeItem(item: Item): Boolean = item is UserPost

    override fun getLayoutId(): Int = R.layout.item_post

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemPostBinding, UserPost> {
        val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding)
    }
}

class PostViewHolder(
    binding: ItemPostBinding
) : BaseViewHolder<ItemPostBinding, UserPost> (binding) {

    override fun onBind(item: UserPost) = with(binding) {
        tvCommentCount.text = item.commentsCount.toString()
        tvLikesCount.text = item.likesCount.toString()
        tvTitle.text = item.getPostDescription()
        ivPostImage.setImageDrawable(item.getPostDrawable())
    }

    private fun UserPost.getPostDrawable() = ContextCompat.getDrawable(binding.root.context, imageResId)

    private fun UserPost.getPostDescription() =
        SpannableStringBuilder("$userNickname $text").apply {
            setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                userNickname.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
}