package com.example.soulsync

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.soulsync.R
import com.example.soulsync.databinding.RegisterviewBinding
import com.example.soulsync.events.LoginEvent
import com.example.soulsync.events.RegistrationEvents
import com.example.soulsync.viewmodels.RegistrationViewmodel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterActivity: AppCompatActivity(){
    lateinit var binding: RegisterviewBinding
    lateinit var registrationViewmodel: RegistrationViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerview)

        registrationViewmodel = RegistrationViewmodel(Firebase.auth,
            FirebaseDatabase
                .getInstance("https://thesoulsync-39d5f-default-rtdb.asia-southeast1.firebasedatabase.app")
                .reference)
        binding = RegisterviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userName2.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    registrationViewmodel.onEvent(RegistrationEvents.setEmail(s.toString()))
                }
            }
        )

        binding.userName.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    registrationViewmodel.onEvent(RegistrationEvents.setPass1(s.toString()))
                }
            }
        )

        binding.userName3.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    registrationViewmodel.onEvent(RegistrationEvents.setPass2(s.toString()))
                }
            }
        )

        binding.Login.setOnClickListener {
            registrationViewmodel.onEvent(RegistrationEvents.Register)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                registrationViewmodel.state.collect{
                    if(it.completed){
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }
                }
            }
        }
    }
}