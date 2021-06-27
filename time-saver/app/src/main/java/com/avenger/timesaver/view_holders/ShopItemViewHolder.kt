package com.avenger.timesaver.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.interfaces.ShopItemClickListener
import com.avenger.timesaver.models.ItemForSale
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.shop_item.view.*

class ShopItemViewHolder(var itemView: View,var listener: ShopItemClickListener) : RecyclerView.ViewHolder(itemView) {
    fun setData( itemForSale: ItemForSale,position:Int ) {
        itemView.apply {
            Glide.with(ivItemImage).load(itemForSale.image).into(ivItemImage)
            tvTitle.text = itemForSale.title
            tvCategory.text = itemForSale.category
            tvDescription.text = itemForSale.description
            tvPrice.text= itemForSale.price.toString()

        }
        itemView.setOnClickListener {
            listener.shopItemClicked(itemForSale,position)
        }

    }
}