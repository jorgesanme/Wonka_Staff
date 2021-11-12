package com.example.wonka_staff.models



data class PersonModel (
    val last_name: String,
    val description: String,
    val image: String,
    val profession: String,
    val quota: String,
    val height: Long,
    val first_name: String,
    val country: String,
    val age: Long,
    val favorite: Favorite,
    val gender: String,
    val email: String
)

