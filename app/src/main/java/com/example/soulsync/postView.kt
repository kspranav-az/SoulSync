package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.soulsync.databinding.ActivityPostViewBinding
import com.example.soulsync.viewmodels.PostViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class postView : AppCompatActivity() {
    private lateinit var binding: ActivityPostViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)
        binding = ActivityPostViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = Firebase.auth
        var postViewModel : PostViewModel = PostViewModel(auth, FirebaseDatabase.getInstance("https://soulsync-8c7b0-default-rtdb.asia-southeast1.firebasedatabase.app/").reference)

        binding.imageButton.setOnClickListener {
            postViewModel.viewModelScope.launch {
                postViewModel.post(binding.editTextText.toString())
            }
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