package com.example.wonka_staff.models

import com.squareup.moshi.Json


data class StaffModelElement (
    val current: Long,
    val total: Long,
    val results: List<Result>
)

