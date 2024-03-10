package com.example.soulsync

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.soulsync.R
import com.example.soulsync.database.WriteData
import com.example.soulsync.database.entity.User
import com.example.soulsync.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth


import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityChatBinding


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var sender : User? = intent.getParcelableExtra("senderO",User::class.java)

        auth = Firebase.auth
        var ref = FirebaseDatabase.getInstance("https://soulsync-8c7b0-default-rtdb.asia-southeast1.firebasedatabase.app/").reference

        if(auth.currentUser?.email == "xlr@gmail.com"){
           var wd = WriteData(ref)

            var userRef = ref.child("user") // Assuming users are under "users" node

            val uid = auth.currentUser!!.uid // Replace with the actual UID
            val user : User = User()

            userRef.child(uid).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val user = snapshot.getValue(User::class.java)
                    // user object now contains data for the user with the given UID
                    // (assuming your User class has appropriate fields)
                    if (user != null) {
                        // Handle retrieved user data (e.g., display user information)
                        // ...
                    } else {
                        // Handle case where no user is found with the UID
                        // ...
                    }
                } else {
                    // Handle case where the user node doesn't exist
                    // ...
                }
            }.addOnFailureListener { exception ->
                // Handle potential errors during data retrieval
                // ...
            }


            val cid : String? = sender?.let { wd.createChat(user = user, it.uid) }

            userRef = userRef.child("uid")

            val updatedValues = HashMap<String, Any>()
            if (cid != null) {
                updatedValues.replace("cid",cid)
            }

            userRef.updateChildren(updatedValues)
                .addOnSuccessListener {
                    // Update successful
                    // ...
                }
                .addOnFailureListener { exception ->
                    // Handle potential errors during update
                    // ...
                }
        }else{
            var cuser : User = User()
            var ref = FirebaseDatabase.getInstance("https://soulsync-8c7b0-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
            var userRef = ref.child("user").equalTo("xlr@gmail.com","email")

            userRef.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                     cuser = snapshot.getValue(User::class.java)!!
                    // user object now contains data for the user with the given UID
                    // (assuming your User class has appropriate fields)
                    if (cuser != null) {
                        // Handle retrieved user data (e.g., display user information)
                        // ...
                    } else {
                        // Handle case where no user is found with the UID
                        // ...
                    }
                } else {
                    // Handle case where the user node doesn't exist
                    // ...
                }
            }.addOnFailureListener { exception ->
                // Handle potential errors during data retrieval
                // ...
            }

            var mcid = cuser.cid

        }
    }
}