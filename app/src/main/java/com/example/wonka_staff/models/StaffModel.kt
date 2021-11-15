package com.example.wonka_staff.models

import com.squareup.moshi.Json


data class StaffModelElement (
    val current: Long,
    val total: Long,
    val results: List<Result>
)

data class Result (
    @Json(name ="first_name")val firstName: String,
    @Json(name ="last_name")val lastName: String,
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


