package com.androidapp.containerprogect.adapter.fingerprint

import android.view.LayoutInflater
import android.view.ViewGroup
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.adapter.BaseViewHolder
import com.androidapp.containerprogect.adapter.Item
import com.androidapp.containerprogect.adapter.ItemFingerprint
import com.androidapp.containerprogect.databinding.ItemTitleBinding
import com.androidapp.containerprogect.model.FeedTitle

class TitleFingerprint : ItemFingerprint<ItemTitleBinding, FeedTitle> {

    override fun isRelativeItem(item: Item): Boolean = item is FeedTitle

    override fun getLayoutId(): Int = R.layout.item_title

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTitleBinding, FeedTitle> {
        val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
        return TitleViewHolder(binding)
    }
}

class TitleViewHolder(
    binding: ItemTitleBinding
) : BaseViewHolder<ItemTitleBinding, FeedTitle>(binding) {

    override fun onBind(item: FeedTitle) {
        binding.tvFeedTitle.text = item.title
    }

}