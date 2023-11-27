package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CalendarContentRepository {
    private val database = Firebase.database
    private val diaryRef = database.getReference("날짜")

    fun observeContent(date: String, content: MutableLiveData<String>){
        diaryRef.child(date).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                content.postValue(snapshot.value?.toString() ?: "")
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun postContent(date: String, newValue: String){
        diaryRef.child(date).setValue(newValue)
    }

    fun deleteContent(date: String){
        diaryRef.child(date).removeValue()
    }
}