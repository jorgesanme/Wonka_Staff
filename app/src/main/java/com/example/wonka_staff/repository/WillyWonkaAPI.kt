package com.example.wonka_staff.repository

import com.example.wonka_staff.models.StaffModelElement
import okhttp3.Call
import org.json.JSONObject

import retrofit2.http.GET

interface WillyWonkaAPI {

    //var pageNum: Int = 2
    @GET("?page=19")
    suspend fun getStaffList(): StaffModelElement?



}