package com.example.soulsync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.soulsync.databinding.VerifiedUserConnectionBinding
import kotlinx.coroutines.launch

class verifiedUserActivity: AppCompatActivity() {

    lateinit var binding : VerifiedUserConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = VerifiedUserConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

        }

        binding.myButton1.setOnClickListener {
            startActivity(Intent(applicationContext,ChatBotActivity::class.java))
        }
        binding.simpleTextView.setOnClickListener {
            startActivity(Intent(applicationContext,FeedActivity::class.java))
        }

    }
}