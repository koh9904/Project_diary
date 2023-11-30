package com.example.myapplication.dataSource

import android.app.Application
import com.google.firebase.FirebaseApp

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

//        (0.. 10).map {
//            DiaryItem(
//                id = "",
//                userId = "111",
//                content = "Diary Test",
//                date = dateFormat.format(Date()),
//                image = "https://picsum.photos/id/${Random().nextInt(200)}/200/300"
//            ).let { item ->
//                diariesRef.push().key?.let { key ->
//                    diariesRef.child(key)
//                        .setValue(item.copy(id = key))
//                        .addOnSuccessListener {
//
//                        }
//                }
//            }
//        }
    }
}