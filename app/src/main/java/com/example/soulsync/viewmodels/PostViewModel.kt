package com.example.soulsync.viewmodels
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

private val TAG = "PostViewModel"

class PostViewModel(private val auth: FirebaseAuth):ViewModel() {
//    private val _state = MutableStateFlow(PostStates())
//    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PostStates())
}
