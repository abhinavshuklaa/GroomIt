package com.avenger.timesaver.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.avenger.timesaver.MainActivity
import com.avenger.timesaver.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SpalshActivity : AppCompatActivity() {
    val SPLASH_LENGTH = 4000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        Handler().postDelayed(Runnable {
            if (checkLogin()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }, SPLASH_LENGTH)
    }

    private fun checkLogin(): Boolean {
        return false
    }
}