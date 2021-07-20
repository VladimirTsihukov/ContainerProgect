package com.androidapp.containerprogect.adapter.fingerprint

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.adapter.BaseViewHolder
import com.androidapp.containerprogect.adapter.FingerprintAdapter
import com.androidapp.containerprogect.adapter.Item
import com.androidapp.containerprogect.adapter.ItemFingerprint
import com.androidapp.containerprogect.adapter.decorations.HorizontalDividerDecoration
import com.androidapp.containerprogect.databinding.ItemHorizontalListBinding
import com.androidapp.containerprogect.model.HorizontalItem

class HorizontalItemsFingerprint(
    private val fingerprintList: List<ItemFingerprint<*, *>>,
    private val outerDivider: Int,
) : ItemFingerprint<ItemHorizontalListBinding, HorizontalItem> {

    override fun isRelativeItem(item: Item): Boolean = item is HorizontalItem

    override fun getLayoutId(): Int = R.layout.item_horizontal_list

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemHorizontalListBinding, HorizontalItem> {
        val binding = ItemHorizontalListBinding.inflate(layoutInflater)
        return HorizontalItemsHolder(binding, fingerprintList, outerDivider)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<HorizontalItem> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<HorizontalItem>() {
        override fun areItemsTheSame(oldItem: HorizontalItem, newItem: HorizontalItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: HorizontalItem, newItem: HorizontalItem): Boolean =
            oldItem == newItem
    }
}

class HorizontalItemsHolder(
    binding: ItemHorizontalListBinding,
    fingerprints: List<ItemFingerprint<*, *>>,
    outerDivider: Int,
) : BaseViewHolder<ItemHorizontalListBinding, HorizontalItem>(binding) {

    private val fingerprintAdapter = FingerprintAdapter(fingerprints)

    init {
        with(binding.rvHorizontalItems) {
            adapter = fingerprintAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(HorizontalDividerDecoration(50, outerDivider))
        }
    }

    override fun onBind(item: HorizontalItem) {
        super.onBind(item)
        fingerprintAdapter.submitList(item.items)
        binding.rvHorizontalItems.restoreState(item.state)
    }

    override fun onViewDetached() {
        item.state = binding.rvHorizontalItems.layoutManager?.onSaveInstanceState() ?: return
    }

    private fun RecyclerView.restoreState(parcelable: Parcelable?) {
        if (parcelable == null) return
        layoutManager?.onRestoreInstanceState(parcelable)
    }
}