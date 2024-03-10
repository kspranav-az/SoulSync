package com.example.soulsync.states

import com.example.soulsync.database.entity.Post

data class PostStates (
    var body : String = "" ,
    var comment:ArrayList<Post> = ArrayList()
)