package com.example.myapplication.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.PeopleData
import com.example.myapplication.repository.OtherRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OtherViewModel: ViewModel() {
    private val repo = OtherRepo()

    val peopleName: MutableLiveData<String> = MutableLiveData()

    fun fetchData(): LiveData<MutableList<PeopleData>> {
        val mutableData = MutableLiveData<MutableList<PeopleData>>()
        repo.observeOther().observeForever {
            mutableData.value = it
        }
        return mutableData
    }

    fun moveUserData(oldUserName: String, newUserName: String) {
        val database = FirebaseDatabase.getInstance()
        val oldRef = database.getReference("OtherUserData/${oldUserName}")
        val newRef = database.getReference("UserData/${newUserName}")

        oldRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue()
                newRef.setValue(data)
                oldRef.removeValue()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //예외 처리
            }
        })
    }
}