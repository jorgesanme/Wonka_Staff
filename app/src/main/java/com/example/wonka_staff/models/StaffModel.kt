package com.example.wonka_staff.models

typealias  StaffList = List<StaffModelElement>

data class StaffModelElement (
    val current: Long,
    val total: Long,
    val results: List<Result>
)

data class Result (
    val first_name: String,
    val last_name: String,
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
    val random_string: String,
    val song: String
)


