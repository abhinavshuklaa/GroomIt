package com.avenger.timesaver.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avenger.timesaver.MainActivity
import com.avenger.timesaver.R
import com.avenger.timesaver.localdatabases.LocalKeys
import com.avenger.timesaver.localdatabases.PreferenceHelper
import com.avenger.timesaver.models.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.shobhitpuri.custombuttons.GoogleSignInButton
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var gso: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient
    val SIGN_IN_CODE = 10
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PreferenceHelper.getSharedPreferences(this)
        initializeSignin()

        findViewById<Button>(R.id.skipLogin).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initializeSignin() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mAuth = FirebaseAuth.getInstance()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInButton = findViewById<GoogleSignInButton>(R.id.signInButton);
        signInButton.setOnClickListener {
            val intent: Intent = googleSignInClient.signInIntent
            startActivityForResult(intent, SIGN_IN_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_CODE) {
            var task: Task<GoogleSignInAccount>? = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try {
            if (task!!.isSuccessful) {
                val account = task.getResult(ApiException::class.java)
                PreferenceHelper.writeBooleanToPreference(LocalKeys.KEY_IS_USER_LOGGED_IN, true)
                updatePreference(account!!)
                Toast.makeText(this, "Welcome ${account.displayName}", Toast.LENGTH_SHORT)
                    .show()
                saveUser(account)

                startActivity(Intent(this, UserDetailsActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Login Error " + task.exception?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {

        }
    }

    private fun updatePreference(account: GoogleSignInAccount) {
        PreferenceHelper.writeBooleanToPreference(LocalKeys.KEY_IS_USER_LOGGED_IN, true)
        PreferenceHelper.writeStringToPreference(LocalKeys.KEY_USER_GOOGLE_ID, account!!.id)

    }

    private fun saveUser(account: GoogleSignInAccount) {
        val database = FirebaseDatabase.getInstance()
        val dbUsers = database.getReference("users").child(account.id!!)
        dbUsers.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    //even if user exit in database the FCM token will be different for each device
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = FirebaseDatabase.getInstance().getReference("users")
                                .child(account.id!!)
                            val token = Objects.requireNonNull(task.result)
                            user.child("token").setValue(token)
                        } else {
                            Log.d("TAG", "onComplete: " + task.exception!!.message)
                        }
                    }
                    Toast.makeText(this@LoginActivity, "Welcomeback", Toast.LENGTH_SHORT).show()
                    return
                }
                if (!snapshot.exists()) {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val token: String = Objects.requireNonNull<String>(it.result)
                            if (account.photoUrl != null) {
                                val user =
                                    UserModel(
                                        id = account.id.toString(),
                                        email = account.email,
                                        firstName = account.displayName,
                                        lastName = "",
                                        gender = "",
                                        profilePic = account.photoUrl.toString(),
                                        contactNumber = "",
                                        location = "",
                                        address = "",
                                        token = token
                                    )
                                dbUsers.setValue(user)
                                    .addOnCompleteListener { it_inside ->
                                        if (it_inside.isSuccessful) {
                                            Toast.makeText(
                                                this@LoginActivity,
                                                "token saved",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }
                                    }
                            }
                            else{
                                val user =
                                    UserModel(
                                        id = account.id.toString(),
                                        email = account.email,
                                        firstName = account.displayName,
                                        lastName = "",
                                        gender = "",
                                        contactNumber = "",
                                        location = "",
                                        address = "",
                                        token = token,
                                        profilePic = ""
                                    )
                                dbUsers.setValue(user)
                                    .addOnCompleteListener { it_inside ->
                                        if (it_inside.isSuccessful) {
                                            Toast.makeText(
                                                this@LoginActivity,
                                                "token saved",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }
                                    }
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}