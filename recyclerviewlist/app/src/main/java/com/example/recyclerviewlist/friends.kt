package com.example.recyclerviewlist

enum class EGender {
    MALE,
    FEMALE,
    LGBT
}
class Friends(val name: String,
              val im: String,
              val gender: EGender)