package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.DiaryItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CalendarContentRepository {
    private val database = Firebase.database
    private val diaryRef = database.getReference(RealtimeDatabaseRef.DIARIES_REFERENCE)

    fun observeContent(date: String, content: MutableLiveData<String>){
        diaryRef.child(date).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                content.postValue(snapshot.value?.toString() ?: "")
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun postContent(diary: DiaryItem){
        diaryRef.push().key?.let { key ->
            diaryRef.child(key)
                .setValue(diary.copy(id = key))
        }
    }

    fun deleteContent(date: String){
        diaryRef.child(date).removeValue()
    }
}