package com.delbel.heritage.presentation.listing

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delbel.heritage.domain.HeritageDetail
import com.delbel.heritage.presentation.R
import com.facebook.drawee.view.SimpleDraweeView

internal class HeritageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {

        fun createFrom(parent: ViewGroup): HeritageViewHolder {
            val view = from(parent.context).inflate(R.layout.htg_item_heritage, parent, false)
            return HeritageViewHolder(view)
        }
    }

    private val image = view.findViewById<SimpleDraweeView>(R.id.htg_item_heritage_image)
    private val title = view.findViewById<TextView>(R.id.htg_item_heritage_title)
    private val subtitle = view.findViewById<TextView>(R.id.htg_item_heritage_subtitle)

    fun bind(heritage: HeritageDetail, onTouch: (HeritageDetail) -> Unit) {
        itemView.setOnClickListener { onTouch(heritage) }

        image.setImageURI(heritage.image)
        title.text = heritage.name
        subtitle.text = heritage.shortInfo
    }
}