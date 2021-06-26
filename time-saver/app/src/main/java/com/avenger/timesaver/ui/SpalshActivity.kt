package com.avenger.timesaver.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.avenger.timesaver.MainActivity
import com.avenger.timesaver.R
import com.avenger.timesaver.localdatabases.LocalKeys
import com.avenger.timesaver.localdatabases.PreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpalshActivity : AppCompatActivity() {
    val SPLASH_LENGTH = 4000L
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        PreferenceHelper.getSharedPreferences(this)
        mAuth = FirebaseAuth.getInstance()

        Handler().postDelayed(Runnable {
            if (checkLogin()) {
                startMainActivity()
            } else {
                startLoginActivity()
            }
        }, SPLASH_LENGTH)
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun checkLogin(): Boolean {
        return if (PreferenceHelper.readBooleanFromPreference(LocalKeys.KEY_IS_USER_LOGGED_IN)) {
            GoogleSignIn.getLastSignedInAccount(this) != null || mAuth.currentUser != null
        } else {
            false
        }
    }
}