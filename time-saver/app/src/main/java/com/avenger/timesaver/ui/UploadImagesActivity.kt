package com.avenger.timesaver.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avenger.timesaver.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList

class UploadImagesActivity : AppCompatActivity() {

    private val imageList = ArrayList<Uri>()
    private val downloadImageList = ArrayList<Uri>()
    var unique = System.currentTimeMillis().toString()
    private var app: FirebaseApp? = null
    private var storage: FirebaseStorage? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_images)
        app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app!!);

        findViewById<Button>(R.id.openGallery).setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(intent, 20)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                if (data?.clipData != null) {
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
            val uri: Uri = imageList[i]

            val shopId = intent.getStringExtra("storeId");

            uploadTask(uri, shopId)
        }
    }

    private fun uploadToRealTimeDatabase(uri: Uri) {
        val ref = FirebaseDatabase.getInstance().getReference("stores")
            .child(intent.getStringExtra("storeId").toString())
        unique += 3
        ref.child("Images").child(unique).setValue(uri.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("TAG", "uploadToRealTimeDatabase: Success")
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

}