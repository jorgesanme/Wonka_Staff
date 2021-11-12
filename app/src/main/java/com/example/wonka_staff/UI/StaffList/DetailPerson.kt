package com.example.wonka_staff.UI.StaffList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
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
                initView(personDetail)
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun initView(personDetail: PersonModel) {
        with(binding) {
            nameTextView.text = personDetail.first_name
            surnameTextView.text = personDetail.last_name
            Glide.with(root).load(personDetail.image).also { it.circleCrop() }.into(imageView)
            ageTextView.text = personDetail.age.toString() + " Years"
            countryTextView.text = personDetail.country
            jobTextView2.text = personDetail.profession
            emailTextView.text = personDetail.email
            heightTextView.text = personDetail.height.toString() + "cm"
            genderTextView.text = personDetail.gender
            colorTextView2.text = personDetail.favorite.color
            foodTextView.text = personDetail.favorite.food
            val contex = binding.root.context
            songIB.setOnClickListener {
                showAlert("Song", personDetail.favorite.song, contex)
            }
            descriptionTextView.setOnClickListener {
                showAlert("Description", personDetail.description, contex)
            }
            quotaTextView.setOnClickListener {
                showAlert("Quote", personDetail.quota, contex)
            }

        }

    }

    fun showAlert(title: String, message: String, contex: Context?) {
        val builder = AlertDialog.Builder(contex!!)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Back", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
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