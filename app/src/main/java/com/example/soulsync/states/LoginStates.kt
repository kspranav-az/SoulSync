package com.example.soulsync.states

import com.example.soulsync.database.entity.User


data class LoginStates(
    var email : String? = "" ,
    var password : String? = "",
    var logged : Boolean = false,
    var failed : Boolean = true,
    var user : User? = null
)