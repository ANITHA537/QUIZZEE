package com.example.quizzee.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzee.R
import com.example.quizzee.activities.QuestionActivity
import com.example.quizzee.models.Quiz
import com.example.quizzee.utils.ColorPicker
import com.example.quizzee.utils.IconPicker

class QuizAdapter(val context: Context, val quizzes:List<Quiz> ):
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textviewTitle.text=quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))//picking a random color for card background
        holder.iconview.setImageResource(IconPicker.getIcon())//picking a random icon
        holder.itemView.setOnClickListener{
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent= Intent(context,QuestionActivity::class.java)
            intent.putExtra("SUBJECT", quizzes[position].title)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }
    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textviewTitle: TextView =itemView.findViewById(R.id.quizTitle)
        var iconview: ImageView =itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView =itemView.findViewById(R.id.cardContainer)
    }
}