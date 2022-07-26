package com.example.homepage.loginSignup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homepage.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private var helper = HelperSignInSignUp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener {

            val email = binding.usersEmail.text.toString()
            val password = binding.usersPasswordType.text.toString()
            val passwordRetype = binding.usersPasswordRetype.text.toString()

            val validityStatus = helper.validateEmailPasswordFormat(email, password, passwordRetype)

            if (validityStatus == "Valid Data") {

                if (fireBaseSignup(email, password)) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }

            } else {
                helper.makeToast(validityStatus)
                binding.usersPasswordRetype.setText("")
            }
        }
        binding.goToSignInPage.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }


    private fun fireBaseSignup(email: String, password: String): Boolean {
        var accountCreationStatus = false
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            accountCreationStatus = if (it.isSuccessful) {

                sendVerificationMail()
                helper.makeToast("Account created with email $email")
                true

            } else {
                val exceptionMessage =
                    it.exception.toString().substring(it.exception.toString().indexOf(":") + 1)
                        .trim()
                helper.makeToast(exceptionMessage)
                false
            }
        }

        return accountCreationStatus
    }

    private fun sendVerificationMail() {

        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->

            if (task.isSuccessful) {
                helper.makeToast("Verification mail has been sent on the email ")
                helper.makeToast("CHECK YOUR EMAILS SPAM BOX FOR VERIFICATION EMAIL")
            } else {

                helper.makeToast("Error Occurred")
            }
        }
    }


}