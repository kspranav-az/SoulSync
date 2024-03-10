package com.example.soulsync.database.entity

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class Chat(
    val uid : String ="",
    val chat : String ="",
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "chat" to chat,
            "timestamp" to timestamp
        )
    }
}
