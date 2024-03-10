package com.example.soulsync.viewmodels
import android.drm.DrmManagerClient.OnEventListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulsync.database.WriteData
import com.example.soulsync.database.entity.User
import com.example.soulsync.events.PostEvents
import com.example.soulsync.events.PostEvents.setPost
import com.example.soulsync.states.PostStates
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

private val TAG = "PostViewModel"

class PostViewModel(private val auth: FirebaseAuth,private val database : DatabaseReference):ViewModel() {
   private val _state = MutableStateFlow(PostStates())
   val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PostStates())
    fun post(body : String){
        auth.currentUser?.uid?.let { it2 ->
            var user : User = User(
                it2, "",
                auth.currentUser?.email
            )



            user.username?.let { WriteData(database).writeNewPost(user.uid, it,"",body) }


    }

}}
