package com.example.quizzee.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizzee.R
import com.example.quizzee.models.Question
import com.example.quizzee.models.Quiz
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
    }

    private fun setUpViews() {//to deserialize the quiz intent
        val quizData=intent.getStringExtra("QUIZ")
        quiz= Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {//for the displaying the reports
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#000B76'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>userAnswer: ${question.userAnswer}</font><br/><br/>")
            builder.append("<font color='#FF1493'>correctAnswer: ${question.correctAnswer}</font><br/><br/>")

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score=0
        for(entry:MutableMap.MutableEntry<String,Question> in quiz.questions.entries){
            val question:Question = entry.value
            if(question.correctAnswer==question.userAnswer){
                score++
            }
        }
        txtScore.text="Your Score : $score"
    }
}