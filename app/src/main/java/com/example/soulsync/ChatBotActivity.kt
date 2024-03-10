package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.soulsync.adapter.chatbotAdapter
import com.example.soulsync.database.entity.Chat
import com.example.soulsync.databinding.ActivityChatBotBinding
import com.example.soulsync.states.ChatStates
import com.example.soulsync.viewmodels.ChatViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChatBotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBotBinding
    //private lateinit var chatRecyclerView: RecyclerView
    //private lateinit var messagebox: EditText
    private lateinit var messageAdpater: chatbotAdapter
    private lateinit var messageLst  : ArrayList<ChatStates>


    //create unqiue room for sender and reciever

    var recieverRoom : String? = null
    var senderRoom   : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
        binding = ActivityChatBotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val recieverUid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        senderRoom = recieverUid + senderUid
        recieverRoom = senderUid + recieverUid

        messageLst = ArrayList()
        messageAdpater = chatbotAdapter(this, messageLst)

        //adding message to dataBase
        binding.sntButton.setOnClickListener {
            val messsage = binding.TextBox.text.toString()
            val messageObject: String //complete this to pass all parameter of chat state


            var chatViewmodel: ChatViewModel = ChatViewModel(
                FirebaseDatabase.getInstance("https://soulsync-8c7b0-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .reference
            )

            binding.TextBox.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }
                }
            )



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
                val i = Intent(applicationContext, FeedActivity::class.java)
                i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
                i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                i.putExtra("EXIT", true)
                startActivity(i)
                finish()
            }
            binding.msg.setOnClickListener {
                val i = Intent(applicationContext, verifiedUserActivity::class.java)
                i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
                i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                i.putExtra("EXIT", true)
                startActivity(i)
                finish()

            }
        }

    }}