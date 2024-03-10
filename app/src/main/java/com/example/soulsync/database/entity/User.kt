package com.example.soulsync.database.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uid: String = "",
    var username: String? = "",
    var email: String? = "",
    var verification: Boolean = false,
    var isactive : Boolean = true,
    var connected : Boolean = false,
) : Parcelable
