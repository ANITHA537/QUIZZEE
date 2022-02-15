package com.example.quizzee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzee.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    // [START declare_auth]
    lateinit var firebaseAuth: FirebaseAuth
    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // [START initialize_auth]
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
        btnLogin.setOnClickListener {
            LoginUser()
        }
        //if the user doesn't have an account on clicking the textview it redirects to signup page
        tvSignup.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()//if user clicks back button it should not go back to login page
        }
    }
    private fun LoginUser() {
        val email: String = etemailAddress.text.toString()
        val password: String = etpassword.text.toString()
        //validations
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email/password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()//if user clicks back button it should not go back to login page
            } else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}