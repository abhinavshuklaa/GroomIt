package com.avenger.timesaver.viewHolder

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.models.TipsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout_tips_list.view.*

class TipsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(model: TipsModel) {
        view.apply {
            Picasso.get().load(model.icon).into(ivFeatured)
            tvVideoTitle.setPaintFlags(tvVideoTitle.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
            tvVideoTitle.text = model.name
        }
    }
}