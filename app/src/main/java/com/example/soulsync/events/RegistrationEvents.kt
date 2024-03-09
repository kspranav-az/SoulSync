package com.example.soulsync.events

sealed interface RegistrationEvents{
    data object Register : RegistrationEvents
    data class setEmail(val email : String) : RegistrationEvents
    data class setPass1(val password : String) : RegistrationEvents
    data class setPass2(val password: String) : RegistrationEvents

}