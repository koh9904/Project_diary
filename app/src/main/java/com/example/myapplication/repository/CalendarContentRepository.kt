package com.example.myapplication.repository

import android.util.Log
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
        diaryRef.orderByChild("date").equalTo(date)
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val diaryItems = snapshot.children.mapNotNull { it.getValue(DiaryItem::class.java) }

                    if(diaryItems.isNotEmpty()){
                        val value = diaryItems[0].content
                        content.postValue(value)
                    }else{
                        content.postValue("")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    fun postContent(diary: DiaryItem){
        diaryRef.push().key?.let { key ->
            Log.d("MyApp", "이거 올라갔다: $key, diary: $diary")

            diaryRef.child(key)
                .setValue(diary)

        }
    }

    fun deleteContent(date: String){
        val query = diaryRef.orderByChild("date").equalTo(date)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    childSnapshot.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


}