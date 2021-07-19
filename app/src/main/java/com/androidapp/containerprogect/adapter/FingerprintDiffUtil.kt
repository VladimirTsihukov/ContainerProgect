package com.androidapp.containerprogect.adapter

import androidx.recyclerview.widget.DiffUtil

class FingerprintDiffUtil(
    private val fingerprints: List<ItemFingerprint<*,*>>,
    private val oldList: List<Item>,
    private val newList: List<Item>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem::class != newItem::class) return false

        return getItemCallback(oldItem).areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem::class != newItem::class) return false

        return getItemCallback(oldItem).areContentsTheSame(oldItem, newItem)
    }

    private fun getItemCallback(
        item: Item
    ) : DiffUtil.ItemCallback<Item> = fingerprints.find { it.isRelativeItem(item)}
        ?.getDiffUtil()
        ?.let {
            it as DiffUtil.ItemCallback<Item>
        }
        ?: throw IllegalArgumentException("DiffUtil not found for $item")
}