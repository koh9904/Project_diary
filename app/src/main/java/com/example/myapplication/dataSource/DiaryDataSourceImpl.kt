package com.example.myapplication.dataSource

import com.example.myapplication.DiaryItem
import com.example.myapplication.repository.RealtimeDatabaseRef.diariesRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class DiaryDataSourceImpl : DiaryDataSource {
    override fun update(diaryItem: DiaryItem) {
        diariesRef
            .child(diaryItem.id)
            .setValue(diaryItem)
    }

    override fun delete(id: String) {
        diariesRef.child(id)
            .removeValue()
    }

    override fun getAll(
        successListener: (List<DiaryItem>) -> Unit
    ) {
        diariesRef
            .orderByChild("date")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val diaries = mutableListOf<DiaryItem>()
                    kotlin.runCatching {
                        snapshot.children.map {
                            it.getValue(DiaryItem::class.java)
                        }.forEach {
                            if(it != null) diaries.add(it)
                        }
                        successListener(diaries.reversed())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    successListener(emptyList())
                }
            })
    }
}