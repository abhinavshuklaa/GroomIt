package com.avenger.timesaver.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.ShopItemClickListener
import com.avenger.timesaver.models.ItemForSale
import com.avenger.timesaver.view_holders.ShopItemViewHolder

class ShopItemAdapter (var list: MutableList<ItemForSale>,var listener: ShopItemClickListener): RecyclerView.Adapter<ShopItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.shop_item,parent,false)
        return ShopItemViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        var itemForSale = list[position]
        holder.setData(itemForSale,position)
    }

    override fun getItemCount(): Int {
   return list.size
    }
}