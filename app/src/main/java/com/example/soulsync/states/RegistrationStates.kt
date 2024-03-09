package com.example.thesoulsync.states

data class RegistrationStates(
    var email : String? = "" ,
    var password : String? = "",
    var password2 : String = "",
    var completed : Boolean = false
)
