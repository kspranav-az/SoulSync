package com.example.soulsync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.soulsync.databinding.VerifiedUserConnectionBinding

class verifiedUserActivity: AppCompatActivity() {

    lateinit var binding : VerifiedUserConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verified_user_connection)

        binding.myButton1.setOnClickListener {
            startActivity(Intent(applicationContext,ChatBotActivity::class.java))
        }
        binding.simpleTextView.setOnClickListener {
            startActivity(Intent(applicationContext,FeedActivity::class.java))
        }

    }
}