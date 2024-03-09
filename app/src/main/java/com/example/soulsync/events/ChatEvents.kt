package com.example.soulsync.events

sealed interface ChatEvents{

    data object connect : ChatEvents
    data class setMessage(val msg:String) : ChatEvents
    data class sendMessage(val uid : String) : ChatEvents
    data object messagerec : ChatEvents
}
