package com.avenger.timesaver.viewHolder

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.TipsModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_tips_list.view.*


class TipsViewHolder(
    private val view: View, private val listener: tipsItemClickedListener
) : RecyclerView.ViewHolder(view) {
    fun setData(model: TipsModel) {
        view.apply {
            Glide.with(ivFeatured).load(model.icon).into(ivFeatured)
            tvVideoTitle.setPaintFlags(tvVideoTitle.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
            tvVideoTitle.text = model.name
            ivImageCard.setOnClickListener {
                listener.onItemClicked(adapterPosition,tvVideoTitle.text.toString())
                val url: Uri =
                    Uri.parse(model.youtube_url) // get your url from list item or your code.

                val intent = Intent(Intent.ACTION_VIEW, url)
                context.startActivity(intent)
            }
        }
    }
}