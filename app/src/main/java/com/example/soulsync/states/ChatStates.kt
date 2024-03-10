package com.example.soulsync.states


class ChatStates {
    var user: String = ""
    var chatid: String = ""
    var message: String = ""
    var active: Boolean = false
    var connected: Boolean = false
    var isuser : Boolean = true

    constructor(){}

    constructor(message: String , isuser: Boolean){
        this.message = message
        this.isuser = isuser
    }
}