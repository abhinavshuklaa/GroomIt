package com.avenger.timesaver.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.avenger.timesaver.R
import com.avenger.timesaver.localdatabases.LocalKeys
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
import com.google.firebase.database.FirebaseDatabase

class AddStore : AppCompatActivity(), OnMapReadyCallback {


    private var location: String = "123"
    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private var locationPermissionGranted = false

    var id = "0"
    val name = "Shop Name"
    val address = "#3/4, 2nd Street, 4th Main, RR Nagar"
    val city = "Bangalore"
    val state = "Karnataka"
    val pincode = "560091"
    val contact_1 = "9632174165"
    val contact_2 = "8632174165"
    val services = "23"
    val styles = "12"


    companion object {
        private val TAG = AddStore::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private var locationPermissionGranted = false
        private const val KEY_CAMERA_POSITION = "camera_position"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_store)
        PreferenceHelper.getSharedPreferences(this)

        getLocationPermission()
        if (savedInstanceState != null) {
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment: SupportMapFragment? = supportFragmentManager
            .findFragmentById(R.id.addstoremap) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        showCurrentPlace()
        findViewById<Button>(R.id.createStore).setOnClickListener {
            createStoreInMap()
        }
    }

    fun getUniqueId(): String {
        id = System.currentTimeMillis()
            .toString() + (Math.random() * 201).toInt().toString()
        return id
    }

    private fun createStoreInMap() {
        val ownerId = PreferenceHelper.readStringFromPreference(LocalKeys.KEY_USER_GOOGLE_ID)
        FirebaseDatabase.getInstance().getReference("stores").child(getUniqueId())
            .setValue(
                Shop(
                    id,
                    name,
                    ownerId,
                    address,
                    city,
                    state,
                    null,
                    pincode,
                    location,
                    contact_1,
                    contact_2,
                    services,
                    styles
                )
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("TAG", "createStoreInMap: Success")
                    gotoUploadImageActivity(id)
                } else {
                    Log.d("TAG", "createStoreInMap: Failed")
                }
            }
    }

    private fun gotoUploadImageActivity(id: String) {
        val i = Intent(this, UploadImagesActivity::class.java)
        i.putExtra("storeId", id)
        startActivity(i)
        this.finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        map?.let { map ->
            outState.putParcelable(AddStore.KEY_CAMERA_POSITION, map.cameraPosition)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        // [START_EXCLUDE]
        // [START map_current_place_set_info_window_adapter]
        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        map.setOnMapClickListener(GoogleMap.OnMapClickListener { latLng -> // Creating a marker
            val markerOptions = MarkerOptions()

            // Setting the position for the marker
            markerOptions.position(latLng)
            location = latLng.latitude.toString() + ":" + latLng.longitude.toString()
            // Setting the title for the marker.
            // This will be displayed on taping the marker
            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)

            // Clears the previously touched position
            map.clear()

            // Animating to the touched position
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng))

            // Placing a marker on the touched position
            map.addMarker(markerOptions)
        })
        getLocationPermission()

        updateLocationUI()

        // Get the current location of the device and set the position of the map.
        getDeviceLocation()
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.

                    } else {
                        Log.d("TAG", "Current location is null. Using defaults.")
                        Log.e("TAG", "Exception: %s", task.exception)
                        map?.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, AddStore.DEFAULT_ZOOM.toFloat())
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
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                AddStore.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
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
            AddStore.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

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