package com.example.wonka_staff.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wonka_staff.genders
import com.example.wonka_staff.models.PersonModel
import com.example.wonka_staff.models.Result
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StaffViewModel(val retrofit: Retrofit) : ViewModel() {

    /** DataList*/
    val stateMLD: MutableLiveData<StaffState> = MutableLiveData()
    val state: LiveData<StaffState>
        get() = stateMLD
    var filterList: MutableList<Result> = mutableListOf()

    /** PersonData*/
    val personMLD: MutableLiveData<PersonModel> = MutableLiveData()
    val person: LiveData<PersonModel>
        get() = personMLD

    private val api: WillyWonkaAPI
    private var requestJob: Job? = null

    init {
        api = retrofit.create(WillyWonkaAPI::class.java)
    }


    fun getStaffList(page: Int, gender: String, query: String?) {
        requestJob?.cancel()
        requestJob = viewModelScope.launch(Dispatchers.IO) {
            val response = api.getStaffList("?page=$page")
            if (gender == genders.Both.letter) {
                if (query == null) {
                    filterList = response!!.results as MutableList<Result>
                } else {
                    filterList = response!!.results.filter {
                        it.profession.contains(query!!)
                    } as MutableList<Result>
                }

            } else {
                filterList = response!!.results.filter {
                    it.profession.contains(query!!)
                            && it.gender.contentEquals(gender)
                } as MutableList<Result>
            }
            stateMLD.postValue(StaffState(filterList))
        }
    }

    fun getPersonDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getPersonByID(id)
            personMLD.postValue(response)
        }
    }

}


data class StaffState(val staffList: List<Result>)