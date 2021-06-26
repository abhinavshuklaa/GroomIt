package com.avenger.timesaver.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.OnServiceSelectListener
import com.avenger.timesaver.localdatabases.LocalKeys
import com.avenger.timesaver.models.ShopServicesModel
import com.avenger.timesaver.recyclerview.ServiceAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList

class UploadImagesActivity : AppCompatActivity(), OnServiceSelectListener {

    private val imageList = ArrayList<Uri>()
    private val downloadImageList = ArrayList<Uri>()
    var unique = System.currentTimeMillis().toString()
    private var app: FirebaseApp? = null
    var id = ""
    private var storage: FirebaseStorage? = null
    val adapter = ServiceAdapter(LocalKeys.getAllServiceList(), this)
    val serviceList: ArrayList<ShopServicesModel> = ArrayList()
    var lottieView: LottieAnimationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_images)
        if (intent != null && intent.extras != null) {
            id = intent.getStringExtra("storeId")!!
        }
        app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app!!);

        lottieView = findViewById<LottieAnimationView>(R.id.lottieAnimation)
        lottieView?.setAnimation(R.raw.upload)
        lottieView?.playAnimation()

        val rvService = findViewById<RecyclerView>(R.id.serviceRv)
        rvService.layoutManager = LinearLayoutManager(this)
        rvService.adapter = adapter


        findViewById<Button>(R.id.openGallery).setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(intent, 20)
        }

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            saveTheServices()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data?.clipData != null) {

                    lottieView?.setAnimation(R.raw.uploading)
                    imageList.clear()
                    val countClipData = data.clipData!!.itemCount
                    var currentImageSlect = 0
                    while (currentImageSlect < countClipData) {
                        val ImageUri: Uri = data.clipData!!.getItemAt(currentImageSlect).uri
                        imageList.add(ImageUri)
                        currentImageSlect += 1
                    }
                    if (imageList.size != 0) {
                        uploadImages()
                    }
                } else {
                    Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun uploadImages() {
        downloadImageList.clear()
        for (i in 0 until imageList.size) {
            lottieView?.setAnimation(R.raw.uploading)
            val uri: Uri = imageList[i]

            uploadTask(uri, id)
        }
    }

    private fun uploadToRealTimeDatabase(uri: Uri) {
        val ref = FirebaseDatabase.getInstance().getReference("stores")
            .child(id)
        unique += 3
        ref.child("Images").child(unique).setValue(uri.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("TAG", "uploadToRealTimeDatabase: Success")
                lottieView?.setAnimation(R.raw.uploadingcompleted)
                lottieView?.playAnimation()
            } else Log.d("TAG", "uploadToRealTimeDatabase: Failed")
        }
    }

    private fun uploadTask(uri: Uri, shopId: String?) {
        val storageReference = FirebaseStorage.getInstance().getReference("shop").child(shopId!!)
            .child(UUID.randomUUID().toString() + getFileExt(uri))
        Log.d("TAG", "uploadTask: ongoinig")
        storageReference
            .putFile(uri)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    throw task.exception!!
                }
                Log.d("TAG", "uploadTask: " + storageReference.downloadUrl)
                storageReference.downloadUrl
            }.addOnCompleteListener {
                if (it.isSuccessful) {
                    val downloadUri = it.result
                    downloadImageList.add(downloadUri)
                    uploadToRealTimeDatabase(downloadUri)
                }
            }
    }

    private fun getFileExt(uri: Uri): String? {
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }

    override fun onServiceClick(service: ShopServicesModel) {
        for (i in 0 until serviceList.size) {
            if (serviceList[i].id == service.id) {
                return
            }
        }
        serviceList.add(service)
    }

    fun saveTheServices() {
        if (serviceList.size <= 2) {
            Toast.makeText(this, "Select More Than 2", Toast.LENGTH_SHORT).show()
        } else {
            uploadTheServices()
        }
    }

    private fun uploadTheServices() {
        FirebaseDatabase.getInstance().getReference("stores")
            .child(id)
            .child("services")
            .setValue(serviceList).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Your Shop Is Online", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "uploadTheServices: Success")
                    this.finish()
                } else {
                    Toast.makeText(this, "Failed to Add", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "uploadTheServices: Failed")
                }

            }
    }

}