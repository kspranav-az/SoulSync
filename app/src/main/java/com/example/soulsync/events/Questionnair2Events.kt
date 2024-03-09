package com.example.soulsync.events

sealed interface Questionnair2Events{
    data class q1(val resp : String) : Questionnair2Events
    data class q2(val resp : String) : Questionnair2Events
    data class q3(val resp : String) : Questionnair2Events

    data object Submit : Questionnair2Events
}