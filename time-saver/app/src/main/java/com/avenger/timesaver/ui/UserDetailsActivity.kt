package com.avenger.timesaver.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.avenger.timesaver.MainActivity
import com.avenger.timesaver.R
import com.avenger.timesaver.localdatabases.LocalKeys
import com.avenger.timesaver.localdatabases.PreferenceHelper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_details.*


@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity(), OnMapReadyCallback,
    AdapterView.OnItemSelectedListener {
    var genders_1 = arrayOf("Male", "Female", "Others")

    var spinner: Spinner? = null
    var textView_msg: TextView? = null

    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private var locationPermissionGranted = false

    companion object {
        private val TAG = UserDetailsActivity::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private var locationPermissionGranted = false


        private const val KEY_CAMERA_POSITION = "camera_position"

    }


    var gender = "Male"
    var address = "Meerut"
    var first_name = "abhinav"
    var lastname = "shukla"
    var location = " "
    var contact_number = "9191919191"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        textView_msg = this.tvSelectedGender
        spinner = this.spinner_1

        spinner!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders_1)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)

        if (savedInstanceState != null) {
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }
        PreferenceHelper.getSharedPreferences(this)
        val userid = PreferenceHelper.readStringFromPreference(LocalKeys.KEY_USER_GOOGLE_ID)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment: SupportMapFragment? = supportFragmentManager
            .findFragmentById(R.id.addstoremap) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        showCurrentPlace()

        findViewById<Button>(R.id.updateUserDetails).setOnClickListener {
            gender = et_password.text.toString()
            address = etLastname.text.toString()
            contact_number = et_repassword.text.toString()
            first_name=et_name.text.toString()
            lastname=etLastname.text.toString()

            saveUserDetails(userid, "gender", gender)
            saveUserDetails(userid, "address", address)
            saveUserDetails(userid, "firstName", first_name)
            saveUserDetails(userid, "lastName", lastname)
            saveUserDetails(userid, "location", location)
            saveUserDetails(userid, "contactNumber", contact_number)
            updatePreferences(gender)
            startMainActivity()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        map?.let { map ->
            outState.putParcelable(KEY_CAMERA_POSITION, map.cameraPosition)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        // [START_EXCLUDE]
        // [START map_current_place_set_info_window_adapter]
        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        map.setOnMapClickListener(OnMapClickListener { latLng -> // Creating a marker
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
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: %s", task.exception)
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
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
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
            Log.i(TAG, "The user did not grant location permission.")

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


    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun updatePreferences(gender: String) {
        PreferenceHelper.writeStringToPreference(LocalKeys.KEY_USER_GENDER, gender)
        PreferenceHelper.writeBooleanToPreference(LocalKeys.KEY_IS_USER_DETAILS_FILLED, true)
    }

    private fun saveUserDetails(userid: String, key: String, value: String) {
        FirebaseDatabase.getInstance().getReference("users").child(userid).child(key)
            .setValue(value).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("TAG", "saveUserDetails: $key")
                } else {
                    Log.d("TAG", "saveUserDetails: Failed $key")
                }
            }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(
            getApplicationContext(),
            "Selected Gender: " + genders_1[p2],
            Toast.LENGTH_SHORT
        ).show()
        textView_msg!!.text = genders_1[p2]

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


}