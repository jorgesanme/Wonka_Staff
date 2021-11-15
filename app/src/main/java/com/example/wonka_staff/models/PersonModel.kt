package com.example.wonka_staff.models

import com.squareup.moshi.Json


data class PersonModel (
    @Json(name ="last_name")val lastName: String,
    val description: String,
    val image: String,
    val profession: String,
    val quota: String,
    val height: Long,
    @Json(name ="first_name")val firstName: String,
    val country: String,
    val age: Long,
    val favorite: Favorite,
    val gender: String,
    val email: String
)

