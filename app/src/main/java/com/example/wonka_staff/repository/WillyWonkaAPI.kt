package com.example.wonka_staff.repository

import com.example.wonka_staff.models.StaffModelElement
import okhttp3.Call
import org.json.JSONObject

import retrofit2.http.GET

interface WillyWonkaAPI {

    @GET("?page=1")
    suspend fun getStaffList(): StaffModelElement?



}