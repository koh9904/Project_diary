package com.example.myapplication.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealtimeDatabaseRef {
    private val database = Firebase.database
    const val DIARIES_REFERENCE = "DIARIES_REFERENCE"
    val diariesRef = database.getReference(DIARIES_REFERENCE)
}