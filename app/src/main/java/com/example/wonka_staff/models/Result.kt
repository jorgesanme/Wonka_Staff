package com.example.wonka_staff.models

import com.squareup.moshi.Json

data class Result (
    @Json(name = ResultName.FIRST_NAME)
    val firstName: String,
    @Json(name = ResultName.LAST_NAME)
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
object ResultName {
    const val FIRST_NAME = "first_name"
    const val LAST_NAME = "last_name"
}