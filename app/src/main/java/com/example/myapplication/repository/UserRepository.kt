package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {
    val database = Firebase.database
    val userRef = database.getReference("user")

    fun observeUser(user: MutableLiveData<String>) {
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user.postValue( snapshot.value.toString() )
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postUser(newValue: String) {
        userRef.setValue(newValue)
    }
}