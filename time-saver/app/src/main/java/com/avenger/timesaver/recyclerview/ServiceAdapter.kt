package com.avenger.timesaver.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.OnServiceSelectListener
import com.avenger.timesaver.models.ShopServicesModel
import com.bumptech.glide.Glide

class ServiceAdapter(
    val serviceList: ArrayList<ShopServicesModel>,
    val onServiceSelectListener: OnServiceSelectListener
) :
    RecyclerView.Adapter<ServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.service_item_layout, parent, false)
        return ServiceViewHolder(v)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.setData(service, onServiceSelectListener)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }
}

class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var cardItem: CardView? = null
    var serviceItemImage: ImageView? = null
    var mainText: TextView? = null
    var subText: TextView? = null

    init {
        itemView.apply {
            cardItem = findViewById(R.id.serviceItemCard)
            serviceItemImage = findViewById(R.id.serviceItemImage)
            mainText = findViewById(R.id.serviceItemTitle)
            subText = findViewById(R.id.serviceSubTitle)
        }
    }

    fun setData(service: ShopServicesModel, onServiceSelectListener: OnServiceSelectListener) {
        if (service.img != null) {
            Glide.with(itemView.context).load(service.img).into(serviceItemImage!!)
        } else {
            Glide.with(itemView.context).load(R.drawable.sampleshop).into(serviceItemImage!!)
        }
        mainText?.text = service.service.toString()
        subText?.text = "₹ " + service.price.toString()
        var itemFlag = false
        cardItem?.setOnClickListener {
            if (itemFlag) {
                cardItem?.setCardBackgroundColor(Color.parseColor("#ffffff"))
                itemFlag = false
            } else {
                cardItem?.setCardBackgroundColor(Color.parseColor("#c4c4c4"))
                itemFlag = true
            }
            onServiceSelectListener.onServiceClick(service)
        }

    }

}