package com.example.quizzee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzee.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    // [START declare_auth]
    lateinit var firebaseAuth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        // [START initialize_auth]
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
        btnSignUp.setOnClickListener {
            SignUpUser()
        }
        tvLogin.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()//if user clicks back button it should not go back to signup page
        }
    }
    private fun SignUpUser(){
        val email:String=etEmailAddress.text.toString()
        val password:String=etPassword.text.toString()
        val confirmPassword: String=etConfirmPassword.text.toString()
        //validations
        if(email.isBlank()||password.isBlank()||confirmPassword.isBlank()){
            Toast.makeText(this,"Email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if(password!=confirmPassword){
            Toast.makeText(this,"Password and ConfirmPassword didn't match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()//if user clicks back button it should not go back to signuppage
            }
            else{
                Toast.makeText(this,"Error creating User", Toast.LENGTH_SHORT).show()
            }
        }
    }
}