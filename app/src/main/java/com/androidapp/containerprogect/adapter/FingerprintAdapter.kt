package com.androidapp.containerprogect.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class FingerprintAdapter(
    private val fingerprint: List<ItemFingerprint<*, *>>
) : RecyclerView.Adapter<BaseViewHolder<ViewBinding, Item>>() {

    private val item = mutableListOf<Item>()

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
        holder.onBind(item[position])
    }

    override fun getItemCount(): Int = item.size

    override fun getItemViewType(position: Int): Int {
        val item = item[position]
        return fingerprint.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }

    fun setItem(newItem: List<Item>) {
        item.clear()
        item.addAll(newItem)
        notifyDataSetChanged()
    }
}