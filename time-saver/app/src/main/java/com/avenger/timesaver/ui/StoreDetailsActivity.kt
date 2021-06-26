package com.avenger.timesaver.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.avenger.timesaver.R
import com.avenger.timesaver.adapter.ShopServicesAdapter
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.ShopFacilitiesModel
import kotlinx.android.synthetic.main.activity_store_details.*
import kotlinx.android.synthetic.main.fragment_tips.*

class StoreDetailsActivity : AppCompatActivity(), tipsItemClickedListener {
    private var list = mutableListOf<ShopFacilitiesModel>()
    private lateinit var adapter: ShopServicesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)
        buildServicesDataList()
        setRecyclerAdapter()

    }

    private fun setRecyclerAdapter() {

        val layoutManager = GridLayoutManager(this, 2)
        recyclerViewForServices.layoutManager = layoutManager
        adapter = ShopServicesAdapter(list, this)
        recyclerViewForServices.adapter = adapter

    }

    private fun buildServicesDataList() {
        list.add(ShopFacilitiesModel("Beauty Spa"))
        list.add(ShopFacilitiesModel("Hair Care"))
        list.add(ShopFacilitiesModel("Body Massage"))
        list.add(ShopFacilitiesModel("Makeup"))
        list.add(ShopFacilitiesModel("Hands & Feet"))
        list.add(ShopFacilitiesModel("Skin Care"))
        list.add(ShopFacilitiesModel("Styling"))


    }


    override fun onItemClicked(position: Int, content: String) {
        Toast.makeText(this, "Service selected is  : $content", Toast.LENGTH_SHORT).show()


    }
}