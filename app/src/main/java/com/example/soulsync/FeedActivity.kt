package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.thesoulsync.adapter.FeedAdapter
import com.example.soulsync.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView : RecyclerView = findViewById<RecyclerView>(R.id.feedRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //val adapter = FeedAdapter(FirebaseDatabase.getInstance("https://soulsync-8c7b0-default-rtdb.asia-southeast1.firebasedatabase.app/").reference)
        //recyclerView.adapter = adapter

//        binding.prf.setOnClickListener {
//            val i = Intent(applicationContext,CalendarActivity::class.java)
//            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
//            i.putExtra("EXIT", true)
//            startActivity(i)
//            finish()
//        }
        binding.prf.setOnClickListener {
            val i = Intent(applicationContext, ChatBotActivity::class.java)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()

        }
        binding.feed.setOnClickListener {
            val i = Intent(applicationContext,FeedActivity::class.java)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()
        }
        binding.msg.setOnClickListener {
            val i = Intent(applicationContext,verifiedUserActivity::class.java)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()

        }
    }
}