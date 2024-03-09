package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class questionNarie2Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questionnaire2_login)
        var btn : Button = findViewById(R.id.myButton1)
        btn.setOnClickListener {
            startActivity(Intent(this,ChatBotActivity::class.java))
        }

    }
}