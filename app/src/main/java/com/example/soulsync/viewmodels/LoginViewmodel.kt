package com.example.soulsync.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulsync.database.entity.User
import com.example.soulsync.events.LoginEvent
import com.example.soulsync.events.RegistrationEvents
import com.example.soulsync.states.LoginStates

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


private val TAG = "LoginViewmodel"
class LoginViewmodel(private val auth: FirebaseAuth) : ViewModel() {

    private val _states  = MutableStateFlow(LoginStates())
    val states = _states.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LoginStates())


    fun onEvent(event : LoginEvent){
        when(event){
            LoginEvent.Login -> {
                //RegistrationViewmodel(auth).onEvent(RegistrationEvents.register(_states.value.email!!,_states.value.password!!))
                _states.value.email?.let {
                    _states.value.password?.let { it1 ->
                        auth.signInWithEmailAndPassword(it, it1)
                            .addOnCompleteListener() { task ->
                                if (task.isSuccessful) {

                                    Log.d(TAG, "signInWithEmail:success")
                                    viewModelScope.launch {
                                        _states.emit(
                                            _states.value.copy(
                                                user = auth.currentUser?.uid?.let { it2 ->
                                                    User(
                                                        it2, "",
                                                        auth.currentUser?.email
                                                    )
                                                },
                                                logged = true,
                                                failed = false
                                            )
                                        )
                                        Log.i(TAG, "signInWithEmail:success" + "${_states.value.user?.uid}")
                                    }


                                } else {
                                    Log.i(TAG, "signInWithEmail:failure")
                                    onEvent(LoginEvent.LoginFailed)

                                }
                            }
                    }
                }
            }
            LoginEvent.LoginFailed -> {
                viewModelScope.launch {

                    _states.emit(
                        _states.value.copy(
                            email = "",
                            password = "",
                            logged = false,
                            failed = true
                        )
                    )

                }

            }

            is LoginEvent.setEmail -> {
                viewModelScope.launch {
                    Log.i(TAG, event.email)
                    _states.emit(
                        _states.value.copy(
                            email = event.email
                        )
                    )
                }
            }
            is LoginEvent.setPassword -> viewModelScope.launch {
                Log.i(TAG, event.password)
                _states.emit(
                    _states.value.copy(
                        password = event.password
                    )
                )
            }
        }
    }

}

class LoginFailedException(message : String) : Exception(message)