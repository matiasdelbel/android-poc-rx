package com.delbel.heritage.presentation.listing

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import com.delbel.heritage.presentation.R

internal class MarginItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        val margin = parent.context.resources.getDimension(R.dimen.htg_listing_item_margin).toInt()

        outRect.left = margin
        outRect.right = margin
        outRect.bottom = margin
        outRect.top = margin
    }
}