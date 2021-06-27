package com.avenger.timesaver.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.avenger.timesaver.R
import com.avenger.timesaver.adapters.ShopItemAdapter
import com.avenger.timesaver.interfaces.ShopItemClickListener
import com.avenger.timesaver.models.ItemForSale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ShopFragment : Fragment(),ShopItemClickListener {
    var list =mutableListOf<ItemForSale>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }
    fun setRecyclerAdapter(){
        var shopItemAdapter = ShopItemAdapter(list,this)
    }
    fun buildRecyclerData(){
         var itemForSale = ItemForSale("https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
             ,735,"https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1"
         ,"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve," +
                     " your everyday",1,"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops","men clothing")

        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
        list.add(itemForSale)
    }

    override fun shopItemClicked(item: ItemForSale, position: Int) {


    }


}