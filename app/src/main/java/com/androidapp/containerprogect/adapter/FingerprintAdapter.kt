package com.androidapp.containerprogect.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

class FingerprintAdapter(
    private val fingerprint: List<ItemFingerprint<*, *>>
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    FingerprintDiffUtil(fingerprint)
) {

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
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, Item>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(currentList[position], payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerprint.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }
}