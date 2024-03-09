package com.example.soulsync.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesoulsync.database.WriteData
import com.example.thesoulsync.events.RegistrationEvents
import com.example.thesoulsync.states.LoginStates
import com.example.thesoulsync.states.RegistrationStates
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val TAG = "RegistrationViewmodel"
class RegistrationViewmodel(private val auth: FirebaseAuth,private val database: DatabaseReference) : ViewModel() {
    private val _state = MutableStateFlow(RegistrationStates())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RegistrationStates()
    )

    fun onEvent(event: RegistrationEvents){
        when(event){


            RegistrationEvents.Register -> {
                _state.value.email?.let {
                    _state.value.password?.let { it1 ->
                        auth.createUserWithEmailAndPassword(it, it1)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    Log.d(TAG, "createUserWithEmail:success")
                                    val user = auth.currentUser
                                    if (user != null) {
                                        WriteData(database).writeNewUser(userId = user.uid,"" , user.email.toString())
                                        viewModelScope.launch {
                                            _state.emit(
                                                _state.value.copy(
                                                    completed = true
                                                )
                                            )
                                        }
                                    }

                                } else {

                                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                                }
                            }
                    }
                }
            }
            is RegistrationEvents.setEmail -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            email = event.email
                        )
                    )
                }
            }
            is RegistrationEvents.setPass1 -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            password = event.password
                        )
                    )
                }
            }

            is RegistrationEvents.setPass2 -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            password2 = event.password
                        )
                    )
                }
            }
        }
    }
}