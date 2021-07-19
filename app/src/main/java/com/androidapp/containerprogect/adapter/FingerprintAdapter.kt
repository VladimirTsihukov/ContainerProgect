package com.androidapp.containerprogect.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class FingerprintAdapter(
    private val fingerprint: List<ItemFingerprint<*, *>>
) : RecyclerView.Adapter<BaseViewHolder<ViewBinding, Item>>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflate = LayoutInflater.from(parent.context)
        return fingerprint.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflate, parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return fingerprint.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }

    fun setItem(newItem: List<Item>) {
        val newList = newItem.toList()
        val fingerprintDiffUtil = FingerprintDiffUtil(fingerprint, items, newList)
        val diffResult = DiffUtil.calculateDiff(fingerprintDiffUtil)
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}