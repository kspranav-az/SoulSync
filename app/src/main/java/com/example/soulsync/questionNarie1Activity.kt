package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import com.example.soulsync.databinding.Quetionaire1LoginBinding
import com.example.soulsync.events.Questionnair2Events

class questionNarie1Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quetionaire1_login)
        var  btn : Button = findViewById(R.id.myButton)
//        btn.setOnClickListener {
//            startActivity(Intent(applicationContext,questionNarie2Activity::class.java))
//        }
    }
}