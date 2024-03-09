package com.example.soulsync.database.entity

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.Calendar

@IgnoreExtraProperties
data class ChatMap(
    val uid: String = "",
    val u2id: String = "",
    val timestamp: Long = Calendar.getInstance().timeInMillis
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "u2id" to u2id,
            "timestamp" to timestamp
        )
    }
}
