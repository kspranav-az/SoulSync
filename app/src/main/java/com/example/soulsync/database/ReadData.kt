package com.example.soulsync.database

import com.example.soulsync.database.entity.Chat
import com.example.soulsync.database.entity.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ReadData(private val database: DatabaseReference) {

    fun getPosts(callback: (List<Post>) -> Unit) {
        val postsQuery: Query = database.child("posts").orderByChild("date").limitToFirst(10)

        postsQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = mutableListOf<Post>()
                for (postSnapshot in dataSnapshot.children) {
                    val post = postSnapshot.getValue(Post::class.java)
                    post?.let { posts.add(it) }
                }
                callback(posts)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle read error
            }
        })
    }


    fun getChatMessages(cid: String, callback: (List<Chat>) -> Unit) {
        val chatReference = database.child("chat/$cid")

        chatReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val chatMessages = mutableListOf<Chat>()
                for (chatSnapshot in dataSnapshot.children) {
                    val chat = chatSnapshot.getValue(Chat::class.java)
                    chat?.let { chatMessages.add(it) }
                }
                callback(chatMessages)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle read error
            }
        })
    }
}
