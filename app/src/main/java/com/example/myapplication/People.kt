package com.example.myapplication

import com.example.object_orientedprogramming.EGender

enum class EGender {
    MALE,
    FEMALE
}
class People(
    val name: String,
    val im: String,
    val gender: EGender
)