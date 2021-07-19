package com.androidapp.containerprogect.adapter.fingerprint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedTitle>() {
        override fun areItemsTheSame(oldItem: FeedTitle, newItem: FeedTitle) = oldItem.title == oldItem.title

        override fun areContentsTheSame(oldItem: FeedTitle, newItem: FeedTitle) = oldItem == oldItem
    }
}

class TitleViewHolder(
    binding: ItemTitleBinding
) : BaseViewHolder<ItemTitleBinding, FeedTitle>(binding) {

    override fun onBind(item: FeedTitle) {
        super.onBind(item)
        binding.tvFeedTitle.text = item.title
    }

}