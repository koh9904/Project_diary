package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiaryItem (
    val id: String = "",
    val userId: String = "",
    var content: String = "",
    var date: String = "",
    var image: String = ""
): Parcelable