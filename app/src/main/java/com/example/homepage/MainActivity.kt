package com.example.homepage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.homepage.onBoarding.OnBoarding1
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        auth = Firebase.auth
        enablePersistence()

        val nextButton = findViewById<Button>(R.id.get_started)
        nextButton.setOnClickListener {
            val intent = Intent(this, OnBoarding1::class.java)
            startActivity(intent)
        }


    }

    public override fun onStart() {
        super.onStart()
        if (auth.currentUser?.isEmailVerified == true)
            updateUI(auth.currentUser)

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.i("LoginActivity", "Update UI Called")
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun enablePersistence() {
        // [START rtdb_enable_persistence]
        Firebase.database.setPersistenceEnabled(true)
        // [END rtdb_enable_persistence]
    }


}
