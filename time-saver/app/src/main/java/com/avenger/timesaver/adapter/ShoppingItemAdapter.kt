package com.avenger.timesaver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.ShoppingItemModel
import com.avenger.timesaver.viewHolder.ShoppingItemViewHolder

class ShoppingItemAdapter(private var modelList: List<ShoppingItemModel>, private val listener: tipsItemClickedListener) : RecyclerView.Adapter<ShoppingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_shopping_item, parent, false)
        return ShoppingItemViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val model = modelList[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return modelList.size

    }

    fun updateData(list: List<ShoppingItemModel>) {
        modelList = list
        notifyDataSetChanged()
    }


}