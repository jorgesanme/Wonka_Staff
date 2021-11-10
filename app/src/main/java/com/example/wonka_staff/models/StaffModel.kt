package com.example.wonka_staff.models


typealias StaffModel = ArrayList<StaffModelElement>

data class StaffModelElement (
    val firstName: String,
    val lastName: String,
    val favorite: Favorite,
    val gender: String,
    val image: String,
    val profession: String,
    val email: String,
    val age: Long,
    val country: String,
    val height: Long,
    val id: Long
)

data class Favorite (
    val color: String,
    val food: String,
    val randomString: String,
    val song: String
)
