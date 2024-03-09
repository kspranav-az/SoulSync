package com.example.soulsync.database.entity

data class User(
    var uid: String = "",
    var username : String? = "",
    var email : String? = "",
    var verification : Boolean = false
)
