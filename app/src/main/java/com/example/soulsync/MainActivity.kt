package com.example.soulsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.example.soulsync.databinding.ActivityMainBinding
import androidx.lifecycle.viewModelScope
import com.example.soulsync.database.WriteData
import com.example.soulsync.database.entity.Chat
import com.example.soulsync.database.entity.QResponse
import com.example.soulsync.database.entity.User
import com.example.soulsync.events.LoginEvent
import com.example.soulsync.viewmodels.LoginViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var loginViewmodel: LoginViewmodel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        auth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewmodel = LoginViewmodel(auth)





        binding.userName2.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    loginViewmodel.onEvent(LoginEvent.setEmail(s.toString()))
                }
            }
        )

        binding.userName.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    loginViewmodel.onEvent(LoginEvent.setPassword(s.toString()))
                }
            }
        )

        binding.Login.setOnClickListener {
            loginViewmodel.onEvent(LoginEvent.Login)


        }

        binding.signup.setOnClickListener {
            val i =  Intent(applicationContext, RegisterActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                loginViewmodel.states.collect{
                    if(it.logged){
                        Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
                        val i = Intent(applicationContext,questionNarie1Activity::class.java)
                        startActivity(i)
                    }
                }
            }
        }

    }
}
