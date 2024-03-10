package com.example.soulsync.events

sealed interface PostEvents {

    data object post : PostEvents

    data class setPost(val msg:String) : PostEvents

}