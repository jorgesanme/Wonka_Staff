package com.example.wonka_staff.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class PersonModel (
    @Json(name = PersonModelName.LAST_NAME)
    val lastName: String,
    val description: String,
    val image: String,
    val profession: String,
    val quota: String,
    val height: Long,
    @Json(name = PersonModelName.FIRST_NAME)
    val firstName: String,
    val country: String,
    val age: Long,
    val favorite: Favorite,
    val gender: String,
    val email: String
)
object PersonModelName {
    const val FIRST_NAME = "first_name"
    const val LAST_NAME = "last_name"
}

