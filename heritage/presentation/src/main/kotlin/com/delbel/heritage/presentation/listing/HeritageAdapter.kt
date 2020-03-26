package com.delbel.heritage.presentation.listing

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.delbel.heritage.domain.HeritageDetail

internal class HeritageAdapter(
    private val onTouch: (HeritageDetail) -> Unit
) : PagedListAdapter<HeritageDetail, HeritageViewHolder>(HeritageDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeritageViewHolder.createFrom(parent)

    override fun onBindViewHolder(holder: HeritageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onTouch) }
    }

    object HeritageDiffCallback : DiffUtil.ItemCallback<HeritageDetail>() {

        override fun areItemsTheSame(oldItem: HeritageDetail, newItem: HeritageDetail) =
            oldItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: HeritageDetail, newItem: HeritageDetail) =
            oldItem == newItem
    }
}