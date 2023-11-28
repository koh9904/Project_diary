package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SettingRepository {
    private val database = Firebase.database
    private val nameRef = database.getReference("user")

    fun observeName(name: MutableLiveData<String>){
        nameRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name.postValue(snapshot.value?.toString() ?: "")
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    fun postName(newName: String){
        nameRef.setValue(newName)
    }
}