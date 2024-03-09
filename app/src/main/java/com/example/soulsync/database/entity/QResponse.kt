package com.example.soulsync.database.entity

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.Calendar

@IgnoreExtraProperties
data class QResponse(
    val q1: String = "",
    val q2: String = "",
    val q3: String = "",
    var date: Long = Calendar.getInstance().timeInMillis,
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "q1" to q1,
            "q2" to q2,
            "q3" to q3,
            "date" to date
        )
    }
}
