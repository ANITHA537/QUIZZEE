package com.example.quizzee.models

data class Question(
   var description:String="",
   var option1:String="",
   var option2:String="",
   var option3:String="",
   var option4:String="",
   var correctAnswer:String="",
   var userAnswer:String=""
)

