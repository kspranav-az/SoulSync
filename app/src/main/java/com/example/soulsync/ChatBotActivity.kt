package com.example.soulsync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.soulsync.adapter.chatbotAdapter
import com.example.soulsync.states.ChatStates
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONObject
import com.example.soulsync.databinding.ActivityChatBotBinding
import java.net.HttpURLConnection

import java.net.URL

class ChatBotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBotBinding
    private lateinit var messageAdpater: chatbotAdapter
    private lateinit var messageLst: ArrayList<ChatStates>

    private val senderUid = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
        binding = ActivityChatBotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")

        messageLst = ArrayList()
        messageAdpater = chatbotAdapter(this, messageLst)
        binding.chatbox.adapter = messageAdpater

        // Send message button click listener
        binding.sntButton.setOnClickListener {
            val message = binding.TextBox.text.toString()
            if (message.isNotEmpty()) {
                sendMessageToAPI(message)
                binding.TextBox.text.clear() // Clear message box after sending
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle profile, feed, and messages button clicks
        binding.prf.setOnClickListener {
            // Handle profile button click (your implementation)
        }
        binding.feed.setOnClickListener {
            // Handle feed button click (your implementation)
        }
        binding.msg.setOnClickListener {
            // Handle messages button click (your implementation)
        }
    }

    private fun sendMessageToAPI(message: String) {
        val url = URL("http://127.0.0.1:8000/chat/")
        val connection = url.openConnection() as HttpURLConnection
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json")
        connection.requestMethod = "POST"

        val jsonParam = JSONObject().put("message", message)
        val jsonString = jsonParam.toString()

        try {
            connection.outputStream.write(jsonString.toByteArray())
            connection.outputStream.flush()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                // Update chat view with response
                val receivedMessage = JSONObject(response).getString("message")
                messageLst.add(ChatStates(message,true)) // Add sent message
                messageLst.add(ChatStates(receivedMessage, false)) // Add received message
                messageAdpater.notifyDataSetChanged()
            } else {
                Log.e("ChatActivity", "Error sending message: ${connection.responseCode}")
                Toast.makeText(this, "Error sending message", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("ChatActivity", "Error sending message", e)
            Toast.makeText(this, "Error sending message", Toast.LENGTH_SHORT).show()
        } finally {
            connection.disconnect()
        }
    }
}
