package com.example.thesoulsync

import com.example.thesoulsync.events.ChatEvents


class ChatStates {
    var user: String = ""
    var chatid: String = ""
    var message: String = ""
    var active: Boolean = false
    var connected: Boolean = false

    constructor(){}

    constructor(message: String?,user: String?){
        if (message != null) {
            this.message = message
        }
        if (user != null) {
            this.user = user
        }
    }
}