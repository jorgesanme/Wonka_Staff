package com.example.wonka_staff.viewModel

import android.provider.CalendarContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wonka_staff.MainActivity
import com.example.wonka_staff.genders
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

    fun getProfessionFilterStaffList(page: Int, gender: String, query: String?) {
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

            if(gender == genders.Both.letter){
                if (query == null){
                    filterList = response!!.results as MutableList<Result>
                }else{
                    filterList = response!!.results.filter {
                        it.profession.contains(query!!) } as MutableList<Result>
                }

            }else{
                filterList = response!!.results.filter {
                    it.profession.contains(query!!)
                            && it.gender.contentEquals(gender)  } as MutableList<Result>
            }

            state.postValue(StaffState(filterList))
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