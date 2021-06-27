package com.avenger.timesaver.viewHolder

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.ShoppingItemModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_shopping_item.view.*
import kotlinx.android.synthetic.main.item_layout_tips_list.view.*

class ShoppingItemViewHolder(
    private val view: View, private val listener: tipsItemClickedListener
) : RecyclerView.ViewHolder(view) {
    fun setData(model: ShoppingItemModel) {
        view.apply {
            Glide.with(shopImage).load(model.imageUrl).into(shopImage)
            shopBrand.text = model.title
            shopTitle.text = "Price: ${model.price.toString()}"
            shopPrice.text = model.category
            shopCard.setOnClickListener {
                listener.onItemClicked(adapterPosition, model.link.toString())
                val url: Uri =
                    Uri.parse(model.link) // get your url from list item or your code.

                val intent = Intent(Intent.ACTION_VIEW, url)
                context.startActivity(intent)
            }
        }
    }
}