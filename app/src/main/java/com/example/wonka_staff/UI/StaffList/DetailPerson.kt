package com.example.wonka_staff.UI.StaffList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wonka_staff.databinding.ActivityDetailPersonBinding
import com.example.wonka_staff.models.PersonModel
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

class DetailPerson() : AppCompatActivity() {
    lateinit var personDetail: PersonModel
    lateinit var binding: ActivityDetailPersonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPersonBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)

        }
        val incomingID = intent.getStringExtra("personId")

        CoroutineScope(Dispatchers.IO).launch {
            if (incomingID != null) {
                personDetail = downloadPersonDetail(incomingID.toInt())
            }
            withContext(Dispatchers.Main) {
                cleanView()
                initView(personDetail)
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }
    private fun initView(personDetail: PersonModel) {
        binding.personID.text = personDetail.first_name
    }
    private fun cleanView(){
        binding.personID.text = ""
    }


}

private suspend fun downloadPersonDetail(id: Int): PersonModel {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient().newBuilder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api: WillyWonkaAPI = retrofit.create(WillyWonkaAPI::class.java)
        val response = api.getPersonByID(id.toString())
        response!!
    }
}