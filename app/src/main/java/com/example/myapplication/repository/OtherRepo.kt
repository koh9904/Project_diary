package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.PeopleData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class OtherRepo {
    fun observeOther(): LiveData<MutableList<PeopleData>> {
        val mutableData = MutableLiveData<MutableList<PeopleData>>()
        val otherRef = Firebase.database.getReference("OtherUserData")
        otherRef.addValueEventListener(object : ValueEventListener {
            val listData: MutableList<PeopleData> = mutableListOf<PeopleData>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (peopleSnapshot in snapshot.children) {
                        val getData = peopleSnapshot.getValue(PeopleData::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }
}