package com.example.wonka_staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wonka_staff.databinding.ActivityMainBinding
import com.example.wonka_staff.models.Result
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    var page: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
        }

        binding.btNextPage.setOnClickListener {
            if (page < 20) {
                page++
                binding.page.text = page.toString()
            }
        }
        binding.btPreviusPage.setOnClickListener{
            if (page > 1){
                page--
                binding.page.text = page.toString()

            }
        }

            CoroutineScope(Dispatchers.IO).launch() {
                val staffList: MutableList<Result> = downloadStaffList(page) as MutableList<Result>
                var filterList: MutableList<Result> = mutableListOf()
                withContext(Dispatchers.Main) {
                    val adapter = StaffRecycleAdapter()
                    binding.staffRV.adapter = adapter
                    adapter.staffList = staffList
                    adapter.notifyDataSetChanged()
                    filterList = staffList.filter { it.gender.equals('F') } as MutableList<Result>
                    print(filterList)
                }
            }
        }


    }


    private suspend fun downloadStaffList(page: Int): List<Result> {
        return withContext(Dispatchers.IO) {
            val client = OkHttpClient().newBuilder().build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val api: WillyWonkaAPI = retrofit.create(WillyWonkaAPI::class.java)
            val response = api.getStaffList("?page=$page")
//            response!!.results.filter { it.gender.equals("M") }
            response!!.results
        }
    }

