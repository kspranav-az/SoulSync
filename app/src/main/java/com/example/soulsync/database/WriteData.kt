package com.example.soulsync.database


import android.util.Log
import com.example.soulsync.database.entity.Chat
import com.example.soulsync.database.entity.ChatMap
import com.example.soulsync.database.entity.Comment
import com.example.soulsync.database.entity.Post
import com.example.soulsync.database.entity.QResponse
import com.example.soulsync.database.entity.User
import com.google.firebase.database.DatabaseReference

private const val TAG = "WriteData"

class WriteData(private val database: DatabaseReference) {

    fun writeNewUser(userId: String, username: String, email: String , verification : Boolean = false) {
        val user = User(userId, username, email , verification)
        val userReference = database.child("users").child(userId)

        userReference.setValue(user)
            .addOnSuccessListener {
                Log.d(TAG, "User inserted successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to insert user: $e")
            }
    }

    fun writeNewPost(userId: String, username: String, title: String, body: String): String? {
        val key = database.child("posts").push().key

        val post = Post(userId, username, title, body)
        val postValues = post.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/posts/$key" to postValues,
            "/user-posts/$userId/$key" to postValues
        )

        database.updateChildren(childUpdates)
            .addOnSuccessListener {
                Log.d(TAG, "Post inserted successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to insert post: $e")
            }
        return key
    }

    fun writeNewComment(userId: String, postId: String, username: String, body: String) {
        val key = database.child("posts/$postId/comment").push().key

        val comment = Comment(userId, username, body)
        val commentValues = comment.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/posts/$postId/comments/$key" to commentValues,
            "/user-comments/$userId/$key" to commentValues
        )

        database.updateChildren(childUpdates)
            .addOnSuccessListener {
                Log.d(TAG, "Comment inserted successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to insert comment: $e")
            }
    }

    fun createChat(user: User, user2: String): String? {
        val key = database.child("chatmap").push().key ?: run {
            Log.e(TAG, "Couldn't get push key for chatmap")
            return null
        }

        val chatmap = ChatMap(user.uid, user2)
        val chatmapUpdates = hashMapOf<String, Any>(
            "/chatmap/$key" to chatmap
        )

        database.updateChildren(chatmapUpdates)
            .addOnSuccessListener {
                Log.d(TAG, "Chatmap created successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to create chatmap: $e")
            }

        return key
    }

    fun writeNewChat(chat: Chat, cid: String) {
        val key = database.child("chat/$cid/").push().key ?: run {
            Log.e(TAG, "Couldn't get push key for chat")
            return
        }

        val chatdata = chat.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/chat/$cid/$key" to chatdata
        )

        database.updateChildren(childUpdates)
            .addOnSuccessListener {
                Log.d(TAG, "Chat message inserted successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to insert chat message: $e")
            }
    }

    fun writeNewResponse(response: QResponse, userid : String): String? {
        val key = database.child("response/$userid/").push().key
        val respdata = response.toMap()

        val childUpdate = hashMapOf<String, Any>(
            "/response/$userid/$key" to response
        )
        database.updateChildren(childUpdate)

        return key
    }
}
