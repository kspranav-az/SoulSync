package com.example.soulsync.states

data class RegistrationStates(
    var email : String? = "" ,
    var password : String? = "",
    var password2 : String = "",
    var completed : Boolean = false
)
