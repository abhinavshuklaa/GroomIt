package com.avenger.timesaver.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.CameraMoverInterface
import com.avenger.timesaver.models.Shop
import com.bumptech.glide.Glide

class NearByAdapter(
    val nearByList: ArrayList<Shop>,
    val cameraMoveInterface: CameraMoverInterface
) : RecyclerView.Adapter<NearByViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearByViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.nearby_item_layout, parent, false)
        return NearByViewHolder(v)
    }

    override fun onBindViewHolder(holder: NearByViewHolder, position: Int) {
        val shop = nearByList[position]
        holder.setData(shop, cameraMoveInterface)
    }

    override fun getItemCount(): Int {
        return nearByList.size
    }
}

class NearByViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cardItem: CardView? = null
    var nearByItemIcon: ImageView? = null
    var nearByItemTitle: TextView? = null
    var subTitle: TextView? = null
    var distance: TextView? = null

    init {
        cardItem = itemView.findViewById<CardView>(R.id.nearByItemLayout)
        nearByItemIcon = itemView.findViewById<ImageView>(R.id.serviceItemImage)
        nearByItemTitle = itemView.findViewById<TextView>(R.id.serviceItemTitle)
        subTitle = itemView.findViewById<TextView>(R.id.serviceSubTitle)
        distance = itemView.findViewById<TextView>(R.id.nearByDistance)
    }

    fun setData(shop: Shop, cameraMoveInterface: CameraMoverInterface) {
        if (shop.images != null && shop.images.size >= 1 && shop.images.get(0) != null) {
            Glide.with(itemView.context).load(shop.images[0]?.toString()).into(nearByItemIcon!!)
        } else {
            Glide.with(itemView.context).load(R.drawable.sampleshop).into(nearByItemIcon!!)
        }
        nearByItemTitle?.text = shop.name
        subTitle?.text = shop.addressLine
        distance?.text = "approx 4.1KM"
        cardItem?.setOnClickListener {
            cameraMoveInterface.moveCamera(shop)
            //move To another Activity
        }
    }

}