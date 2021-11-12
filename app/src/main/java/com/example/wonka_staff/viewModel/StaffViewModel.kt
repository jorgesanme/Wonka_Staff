package com.example.wonka_staff.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wonka_staff.models.PersonModel
import com.example.wonka_staff.models.Result
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StaffViewModel : ViewModel() {

    val state: MutableLiveData<StaffState> = MutableLiveData()
    val person: MutableLiveData<PersonModel> = MutableLiveData()

    fun getStaffList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient().newBuilder().build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val api: WillyWonkaAPI = retrofit.create(WillyWonkaAPI::class.java)
            val response = api.getStaffList("?page=$page")
            state.postValue(StaffState(response!!.results))
        }
    }
    fun getGenderFilterStaffList(page: Int, gender: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var filterList: MutableList<Result> = mutableListOf()
            val client = OkHttpClient().newBuilder().build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val api: WillyWonkaAPI = retrofit.create(WillyWonkaAPI::class.java)
            val response = api.getStaffList("?page=$page")
            filterList = response!!.results.filter { it.gender.contains(gender) } as MutableList<Result>
            state.postValue(StaffState(filterList)) //response!!.results
        }
    }

        fun getPersonDetail(id: String){
            viewModelScope.launch(Dispatchers.IO) {
                val client = OkHttpClient().newBuilder().build()
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                val api: WillyWonkaAPI = retrofit.create(WillyWonkaAPI::class.java)
                val response = api.getPersonByID(id)
                person.postValue(response)
            }
        }
    }



data class StaffState(val staffList: List<Result>)