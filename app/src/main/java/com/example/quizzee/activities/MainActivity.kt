package com.example.quizzee.activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzee.R
import com.example.quizzee.adapters.QuizAdapter
import com.example.quizzee.models.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: QuizAdapter
    private var quizList= mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
    }
    private fun popularDummyData(){//dummy data for testing
        quizList.add(Quiz("12-10-2021","DAA"))
        quizList.add(Quiz("11-10-2021","SE"))
        quizList.add(Quiz("17-10-2021","FLAT"))
        quizList.add(Quiz("15-10-2021","COA"))
        quizList.add(Quiz("19-10-2021","ADS"))
        quizList.add(Quiz("14-10-2021","DBMS"))
    }
    fun setUpViews() {
        setUpFirestore()
        setUpDrawerLayout()
        setUpRecyclerView()
    }
    private fun setUpFirestore(){
        firestore= FirebaseFirestore.getInstance()
        val collectionReference:CollectionReference=firestore.collection("QUIZZES")
        collectionReference.addSnapshotListener { value, error ->
            if(value==null||error!=null){
                Toast.makeText(this,"Error fetching the data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()//to remove dummy data
            quizList.addAll(value.toObjects(Quiz::class.java))//to fetch data from firestore
            adapter.notifyDataSetChanged()//to update any changes in firestore and refresh it
        }
    }
    private fun setUpRecyclerView() {
        adapter= QuizAdapter(this,quizList)
        quizRecyclerView.layoutManager= GridLayoutManager(this,2)
        quizRecyclerView.adapter=adapter
    }

    //to sync drawable layout to menu icon
    fun setUpDrawerLayout() {
        setSupportActionBar(appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            val intent= Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            mainDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}