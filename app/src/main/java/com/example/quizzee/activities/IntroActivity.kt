package com.example.quizzee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzee.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val auth:FirebaseAuth = FirebaseAuth.getInstance()
        //if the user is already logged in page directly goes to main activity instead of login page
        if(auth.currentUser!=null){
            Toast.makeText(this,"User is already logged in",Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }
        //if the user is not already logged in or the user is logged out
        btnGetStarted.setOnClickListener {
            redirect( "LOGIN")
        }
    }
    private fun redirect(name:String){
        val intent = when(name){
            "LOGIN" -> Intent(this, LoginActivity::class.java)//making a reference to redirect the current page to login page
            "MAIN" -> Intent(this, MainActivity::class.java)//making a reference to redirect the current page to  MainActivitypage i.e. for quiz
            else -> throw Exception("No path Exists")
        }
        startActivity(intent)
        finish()
    }
}