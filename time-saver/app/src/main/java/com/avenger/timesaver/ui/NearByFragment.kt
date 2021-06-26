package com.avenger.timesaver.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.avenger.timesaver.R
import com.avenger.timesaver.localdatabases.PreferenceHelper
import com.avenger.timesaver.models.Shop
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class NearByFragment : Fragment(), OnMapReadyCallback {

    private var location: String = "123"

    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private var locationPermissionGranted = false

    private var storeList = ArrayList<Shop>()

    companion object {
        private val TAG = AddStore::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private var locationPermissionGranted = false

        private const val KEY_CAMERA_POSITION = "camera_position"

    }

    private val googleMap: GoogleMap? = null
    private val options = MarkerOptions()
    private val latlngs: ArrayList<LatLng> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_near_by, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PreferenceHelper.getSharedPreferences(view.context)

        getLocationPermission()
        if (savedInstanceState != null) {
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity?.applicationContext!!)
        val mapFragment: SupportMapFragment? = childFragmentManager
            .findFragmentById(R.id.nearbymap) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        showCurrentPlace()


    }


    override fun onSaveInstanceState(outState: Bundle) {
        map?.let { map ->
            outState.putParcelable(KEY_CAMERA_POSITION, map.cameraPosition)
        }
        super.onSaveInstanceState(outState)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onMapReady(map: GoogleMap) {
        this.map = map

        // [START_EXCLUDE]
        // [START map_current_place_set_info_window_adapter]
        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.

        getLocationPermission()

        updateLocationUI()

        // Get the current location of the device and set the position of the map.
        getDeviceLocation()
        getAllNearByStores(map)

    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(activity?.mainExecutor!!) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.

                    } else {
                        Log.d("TAG", "Current location is null. Using defaults.")
                        Log.e("TAG", "Exception: %s", task.exception)
                        map?.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat())
                        )
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(
                activity?.applicationContext!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                activity?.parent!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionGranted = true
                }
            }
        }
        updateLocationUI()
    }


    @SuppressLint("MissingPermission")
    private fun showCurrentPlace() {
        if (map == null) {
            return
        }
        if (locationPermissionGranted) {
            // Use fields to define the data types to return.
            val placeFields = listOf(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)

            // Use the builder to create a FindCurrentPlaceRequest.
            val request = FindCurrentPlaceRequest.newInstance(placeFields)

            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
        } else {
            // The user has not granted permission.
            Log.i("TAG", "The user did not grant location permission.")

            // Add a default marker, because the user hasn't selected a place.
            map?.addMarker(
                MarkerOptions()
                    .title("title")
                    .position(defaultLocation)
                    .snippet("snippet")
            )
            // Prompt the user for permission.
            getLocationPermission()
        }
    }


    private fun getAllNearByStores(googleMap: GoogleMap) {
        FirebaseDatabase.getInstance().getReference("stores")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    latlngs.clear()
                    storeList.clear()
                    snapshot.children.forEach {
                        try {
                            storeList.add(
                                Shop(
                                    it.child("id").value.toString(),
                                    it.child("name").value.toString(),
                                    it.child("ownerId").value.toString(),
                                    it.child("addressLine").value.toString(),
                                    it.child("city").value.toString(),
                                    it.child("state").value.toString(),
                                    it.child("pinCode").value.toString(),
                                    it.child("location").value.toString(),
                                    it.child("contact_no1").value.toString(),
                                    it.child("contact_no2").value.toString(),
                                    it.child("services").value.toString(),
                                    it.child("styles").value.toString(),
                                )
                            )
                            val ll = it.child("location").value.toString().split(":")
                            Log.d(TAG, "onDataChange: lat " + ll[0])
                            latlngs.add(LatLng(ll[0].toDouble(), ll[1].toDouble()))
                        } catch (e: Exception) {
                            Log.d(TAG, "onDataChange: error")
                        }
                        setNearByStores(map!!,storeList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun setNearByStores(googleMap: GoogleMap,storeList: java.util.ArrayList<Shop>) {
        var x = 0;
        for (point in latlngs) {
            options.position(point)
            options.title(storeList[x].name.toString())
            options.snippet(storeList[x++].addressLine.toString())
            googleMap.addMarker(options)
        }

    }


    private fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

}