package com.avenger.timesaver.viewHolder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.ShopFacilitiesModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_services.view.*

class ShopServicesViewHolder(
    private val view: View, private val listener: tipsItemClickedListener
) : RecyclerView.ViewHolder(view) {
    fun setData(model: ShopFacilitiesModel) {
        view.apply {

            tvItemLayoutServices.text = model.facilityName
            if(model.facilityName.equals("Hair Care")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.hair)
            }else if(model.facilityName.equals("Styling")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.styling)

            }else if(model.facilityName.equals("Skin Care")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.skin)

            }else if(model.facilityName.equals("Hands & Feet")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.hands)

            }else if(model.facilityName.equals("Makeup")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.makeup)

            }else if(model.facilityName.equals("Body Massage")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.massage)

            }else if(model.facilityName.equals("Beauty Spa")){
                ServicesCard.background=ContextCompat.getDrawable(context, R.drawable.spa)

            }


            ServicesCard.setOnClickListener {
                listener.onItemClicked(adapterPosition,tvItemLayoutServices.text.toString())
            }
        }
    }
}