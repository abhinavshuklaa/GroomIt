package com.avenger.timesaver.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avenger.timesaver.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
    }
}