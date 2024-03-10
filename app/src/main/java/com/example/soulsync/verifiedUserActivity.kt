package com.example.soulsync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.soulsync.database.entity.Post
import com.example.soulsync.database.entity.User
import com.example.soulsync.databinding.VerifiedUserConnectionBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.launch
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class verifiedUserActivity: AppCompatActivity() {

    lateinit var binding : VerifiedUserConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = VerifiedUserConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var verified = false
        var senderO : User = User()

        lifecycleScope.launch {
            var activeuser : Query = FirebaseDatabase.getInstance("https://soulsync-8c7b0-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
                .child("user").equalTo(true,"active")

            activeuser.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val sender = mutableListOf<User>()
                    for (senderSnapshot in dataSnapshot.children) {
                        val senderO = senderSnapshot.getValue(User::class.java)
                        break
                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle read error
                }
            })
        }

        binding.proceed.setOnClickListener {
            if(senderO.uid != ""){
                var i = Intent(applicationContext,ChatActivity::class.java)
                i.putExtra("sender" , senderO)
                startActivity(i)
            }

        }


    }
}