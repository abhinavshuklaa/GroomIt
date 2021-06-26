package com.avenger.timesaver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.ShopFacilitiesModel
import com.avenger.timesaver.viewHolder.ShopServicesViewHolder

class ShopServicesAdapter(private var modelList: List<ShopFacilitiesModel>, private val listener: tipsItemClickedListener) : RecyclerView.Adapter<ShopServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopServicesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_services, parent, false)
        return ShopServicesViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ShopServicesViewHolder, position: Int) {
        val model = modelList[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return modelList.size

    }

    fun updateData(list: List<ShopFacilitiesModel>) {
        modelList = list
        notifyDataSetChanged()
    }
    }

