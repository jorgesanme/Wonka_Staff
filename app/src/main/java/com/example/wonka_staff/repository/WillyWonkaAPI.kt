package com.example.wonka_staff.repository

import com.example.wonka_staff.models.PersonModel
import com.example.wonka_staff.models.StaffModelElement
import okhttp3.Call
import org.json.JSONObject

import retrofit2.http.GET
import retrofit2.http.Url

interface WillyWonkaAPI {

    @GET
    suspend fun getStaffList(@Url page: String): StaffModelElement?

    @GET
    suspend fun getPersonByID(@Url id: String): PersonModel?


}