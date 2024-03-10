package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soulsync.databinding.ActivityPostViewBinding

class postView : AppCompatActivity() {
    private lateinit var binding: ActivityPostViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)
        binding = ActivityPostViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            val i = Intent(applicationContext, ChatBotActivity::class.java)
            i.putExtra("EXIT", true)
            startActivity(i)
        }
        binding.button.setOnClickListener {
            val i = Intent(applicationContext,FeedActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()
        }

    }
}