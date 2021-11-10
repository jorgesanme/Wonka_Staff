package com.example.wonka_staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wonka_staff.databinding.ActivityMainBinding
import com.example.wonka_staff.models.StaffModelElement
import com.example.wonka_staff.models.testModel
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
        }
        val adapter = StaffRecycleAdapter()
        binding.staffRV.adapter = adapter

        adapter.staffList = listOf(
            testModel(
                firstName = "jorge",
                lastName = "san",
                gender = "M",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
            ),
            testModel(
                firstName = "Weo",
                lastName = "Mar",
                gender = "F",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg ",
            ),
            testModel(
                firstName = "Emi",
                lastName = "Ko",
                gender = "M",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/3.jpg",
            ),
            testModel(
                firstName = "jorge",
                lastName = "san",
                gender = "M",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
            ),
            testModel(
                firstName = "Weo",
                lastName = "Mar",
                gender = "F",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg ",
            ),
            testModel(
                firstName = "Emi",
                lastName = "Ko",
                gender = "M",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/3.jpg",
            ),
            testModel(
                firstName = "jorge",
                lastName = "san",
                gender = "M",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
            ),
            testModel(
                firstName = "Weo",
                lastName = "Mar",
                gender = "F",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg ",
            ),
            testModel(
                firstName = "Emi",
                lastName = "Ko",
                gender = "M",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/3.jpg",
            ),
        )

        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient().newBuilder().build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val api: WillyWonkaAPI = retrofit.create(WillyWonkaAPI::class.java)
            val response = api.getStaffList()
            print(response?.results?.get(1)?.first_name)
        }
    }
}