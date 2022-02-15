package com.example.quizzee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzee.R
import com.example.quizzee.adapters.OptionAdapter
import com.example.quizzee.models.Question
import com.example.quizzee.models.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    var quizzes:MutableList<Quiz>? =null
    var questions:MutableMap<String,Question>?=null
    var index=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFirestore()//to fetch questions from firestore
        setUpEventListener()//for buttons to respond
    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener{
            index--
            bindviews()
        }
        btnNext.setOnClickListener{
            index++
            bindviews()
        }
        btnSubmit.setOnClickListener{
            Log.d("FINALQUIZ",questions.toString())
            val intent = Intent(this,ResultActivity::class.java)
            val json=Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)

        }
    }
    private fun setUpFirestore() {
        val firestore= FirebaseFirestore.getInstance()
        var subject=intent.getStringExtra("SUBJECT")
        if(subject!=null){
            firestore.collection("QUIZZES").whereEqualTo("title",subject)
                .get()
                .addOnSuccessListener {
                    if(it!=null && !it.isEmpty){
                        quizzes=it.toObjects(Quiz::class.java)
                        questions= quizzes!![0].questions
                        bindviews()
                    }

                }
        }

    }

    private fun bindviews() {//for the previous,next and submit buttons to be visible
        btnPrevious.visibility= View.GONE
        btnSubmit.visibility= View.GONE
        btnNext.visibility= View.GONE
        if(index==1){//first question doesn't need a previous button
            btnNext.visibility= View.VISIBLE
        }
        else if(index==questions!!.size){//that is last question
            btnPrevious.visibility= View.VISIBLE
            btnSubmit.visibility= View.VISIBLE
        }
        else{//index>1 i.e a random question except first and last
            btnPrevious.visibility= View.VISIBLE
            btnNext.visibility=View.VISIBLE
        }
        val question=questions!!["Question$index"]
        question?.let {
            description.text=question.description//it.description
            val optionAdapter=OptionAdapter(this,it)
            optionList.layoutManager=LinearLayoutManager(this)
            optionList.adapter=optionAdapter
            optionList.setHasFixedSize(true)//to increase performance and we know that only 4 options are present for each question
        }

    }

}