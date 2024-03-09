package com.example.soulsync.database.entity

import com.google.firebase.database.Exclude

data class Comment(
    var uid: String? = "",
    var user: String? = "",
    var body: String? = "",
    var helpful: Int = 0
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "user" to user,
            "body" to body,
            "helpful" to helpful
        )
    }
}
