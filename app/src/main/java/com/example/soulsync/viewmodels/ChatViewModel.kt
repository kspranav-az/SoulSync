package com.example.soulsync.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulsync.states.ChatStates
import com.example.soulsync.database.ReadData
import com.example.soulsync.database.WriteData
import com.example.soulsync.database.entity.Chat
import com.example.soulsync.events.ChatEvents
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val TAG = "ChatViewModel"

class ChatViewModel (private val database : DatabaseReference): ViewModel(){
    private val _state = MutableStateFlow(ChatStates())
    val state = _state.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),ChatStates())

    fun onEvent(event : ChatEvents){

        when(event){
            ChatEvents.messagerec -> {
                ReadData(database).getChatMessages(_state.value.chatid){
                    for(i in it){
                        Log.i("ChatviewModel",i.chat)
                    }
                }
            }
            is ChatEvents.sendMessage -> {
                WriteData(database).writeNewChat(Chat(event.uid,_state.value.message),_state.value.chatid)
            }
            is ChatEvents.setMessage -> {
                viewModelScope.launch {
//                    _state.emit(
//                        _state.value.copy(
//
//                        )
//                    )
                }
            }
            ChatEvents.connect -> {
                while (!_state.value.connected){
                    var getPerson = ((database.child("user").
                    child("verification").equalTo(true)).
                    orderByChild("active").equalTo(true)).
                    orderByChild("connected").equalTo(true)

                    Log.i("chatview","${getPerson.get().result.value}")


                }
            }

        }
    }

}