package com.avenger.timesaver.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.avenger.timesaver.localdatabases.PreferenceHelper
import com.avenger.timesaver.models.ShopFacilitiesModel
import com.avenger.timesaver.models.ShopServicesModel
import com.avenger.timesaver.recyclerview.ServiceAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_store_details.*
import kotlinx.android.synthetic.main.fragment_tips.*

class StoreDetailsActivity : AppCompatActivity(), OnServiceSelectListener {
    private var list = mutableListOf<ShopFacilitiesModel>()
    private lateinit var adapter: ShopServicesAdapter

    val shop = NearByFragment.lastShop
    val shopServiceList: ArrayList<ShopServicesModel> = ArrayList()

    companion object {
        val selectedServiceList: ArrayList<ShopServicesModel> = ArrayList()
        var shoperToken: String = ""
        var myToken: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)
        PreferenceHelper.getSharedPreferences(this)
        getAllShopServices()
        getShopKeeperToken()
        getMyFcmToken()
        tvStoreHeaderTitle.text = shop?.name
        DistanceFromYourLocation.text = (Math.random() * 20).toString()
        tvStoreAddress.text = shop?.addressLine + "\n" + shop?.city + "\n" + shop?.pinCode
        tvStoreContactNumberDetails.text = shop?.contact_no1 + "\n" + shop?.contact_no2

        setRecyclerAdapter()
        findViewById<Button>(R.id.btnBookAppointment).setOnClickListener {
            startActivity(Intent(this, SlotBooking::class.java))
        }
    }

    private fun getAllShopServices() {
        FirebaseDatabase.getInstance().getReference("stores").child(shop?.id!!).child("services")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        shopServiceList.clear()
                        snapshot.children.forEach {
                            shopServiceList.add(
                                ShopServicesModel(
                                    it.child("id").value.toString().toInt(),
                                    it.child("img").value.toString().toInt(),
                                    it.child("service").value.toString(),
                                    it.child("price").value.toString().toInt()
                                )
                            )
                        }
                        setRecyclerAdapter()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun getShopKeeperToken() {
        try {
            FirebaseDatabase.getInstance().getReference("users").child(shop?.ownerId!!)
                .child("token")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            Log.d("TAG", "onDataChange: User Found")
                            shoperToken = snapshot.value!!.toString()
                        } else {
                            Log.d("TAG", "onDataChange: User Not Found")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        } catch (e: Exception) {

        }
    }

    fun getMyFcmToken() {
        val uid = PreferenceHelper.readStringFromPreference(LocalKeys.KEY_USER_GOOGLE_ID);
        FirebaseDatabase.getInstance().getReference("users").child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        myToken = snapshot.child("token").value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun setRecyclerAdapter() {

        val layoutManager = LinearLayoutManager(this)
        recyclerViewForServices.layoutManager = layoutManager
        val adapter = ServiceAdapter(shopServiceList, this);
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