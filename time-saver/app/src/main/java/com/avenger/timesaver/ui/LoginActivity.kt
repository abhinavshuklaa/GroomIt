package com.avenger.timesaver.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.avenger.timesaver.MainActivity
import com.avenger.timesaver.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.skipLogin).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}