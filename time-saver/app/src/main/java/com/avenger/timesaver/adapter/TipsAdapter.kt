package com.avenger.timesaver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.TipsModel
import com.avenger.timesaver.viewHolder.TipsViewHolder

class TipsAdapter(private var modelList: List<TipsModel>,private val listener: tipsItemClickedListener) : RecyclerView.Adapter<TipsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_tips_list, parent, false)
        return TipsViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) {
        val model = modelList[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return modelList.size

    }

    fun updateData(list: List<TipsModel>) {
        modelList = list
        notifyDataSetChanged()
    }


}