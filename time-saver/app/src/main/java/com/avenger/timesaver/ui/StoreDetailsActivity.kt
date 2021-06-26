package com.avenger.timesaver.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.avenger.timesaver.R
import com.avenger.timesaver.adapter.ShopServicesAdapter
import com.avenger.timesaver.interfaces.OnServiceSelectListener
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.localdatabases.LocalKeys
import com.avenger.timesaver.models.ShopFacilitiesModel
import com.avenger.timesaver.models.ShopServicesModel
import com.avenger.timesaver.recyclerview.ServiceAdapter
import kotlinx.android.synthetic.main.activity_store_details.*
import kotlinx.android.synthetic.main.fragment_tips.*

class StoreDetailsActivity : AppCompatActivity(), OnServiceSelectListener {
    private var list = mutableListOf<ShopFacilitiesModel>()
    private lateinit var adapter: ShopServicesAdapter

    val shop = NearByFragment.lastShop

    companion object {
        val selectedServiceList: ArrayList<ShopServicesModel> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)

        tvStoreHeaderTitle.text = shop?.name
        DistanceFromYourLocation.text = (Math.random() * 20).toString()
        tvStoreAddress.text = shop?.addressLine + "\n" + shop?.city + "\n" + shop?.pinCode
        tvStoreContactNumberDetails.text = shop?.contact_no1 + "\n" + shop?.contact_no2

        setRecyclerAdapter()

        findViewById<Button>(R.id.btnBookAppointment).setOnClickListener {
            startActivity(Intent(this, SlotBooking::class.java))
        }

    }

    private fun setRecyclerAdapter() {

        val layoutManager = LinearLayoutManager(this)
        recyclerViewForServices.layoutManager = layoutManager
        val adapter = ServiceAdapter(LocalKeys.getAllServiceList(), this);
        recyclerViewForServices.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    override fun onServiceClick(service: ShopServicesModel) {
        for (i in 0 until selectedServiceList.size) {
            if (selectedServiceList[i].id == service.id) {
                return
            }
        }
        selectedServiceList.add(service)
    }
}