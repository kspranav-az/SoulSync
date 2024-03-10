package com.example.soulsync.database.entity

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uid: String = "",
    var username: String? = "",
    var email: String? = "",
    var verification: Boolean = false,
    var isactive : Boolean = true,
    var connected : Boolean = false,
    var cid : String = ""
) : Parcelable{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "username" to username,
            "email" to email,
            "verification" to verification,
            "isactive" to isactive,
            "connected" to  connected,
            "cid" to cid
        )
    }
}
