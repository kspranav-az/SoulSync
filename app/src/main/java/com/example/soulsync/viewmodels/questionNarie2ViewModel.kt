package com.example.soulsync.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulsync.events.Questionnair2Events
import com.example.soulsync.states.Questionnaire2States
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val TAG = "questionNarie2ViewModel"

class questionNarie2ViewModel(private val database: DatabaseReference , private val userid : String):ViewModel() {
    private val _state = MutableStateFlow(Questionnaire2States())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Questionnaire2States())


    fun onEvent(event: Questionnair2Events){
        when(event){
            is Questionnair2Events.q1 -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            q1 = event.resp
                        )
                    )
                }
            }
            is Questionnair2Events.q2 -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            q1 = event.resp
                        )
                    )
                }
            }
            is Questionnair2Events.q3 -> {
                viewModelScope.launch {
                    _state.emit(
                        _state.value.copy(
                            q1 = event.resp
                        )
                    )
                }
            }
            Questionnair2Events.Submit -> {

            }
        }
    }
}
