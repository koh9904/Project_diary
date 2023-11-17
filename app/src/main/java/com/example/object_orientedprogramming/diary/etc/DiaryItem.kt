package com.example.object_orientedprogramming.diary.etc

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiaryItem (
    val id: Int,
    var content: String,
    var date: String,
    var image: String
): Parcelable