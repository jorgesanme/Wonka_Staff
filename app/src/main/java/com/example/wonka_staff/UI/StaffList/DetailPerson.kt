package com.example.wonka_staff.UI.StaffList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wonka_staff.Utils.Utils
import com.example.wonka_staff.databinding.ActivityDetailPersonBinding
import com.example.wonka_staff.models.PersonModel
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.example.wonka_staff.viewModel.StaffViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DetailPerson() : AppCompatActivity(), DIAware {

    override val di: DI by di()
    lateinit var binding: ActivityDetailPersonBinding
    private val viewModel: StaffViewModel by lazy {
        ViewModelProvider(this, direct.instance()).get(StaffViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPersonBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)

        }
        val incomingID = intent.getStringExtra("personId").toString()

        viewModel.getPersonDetail(incomingID)
        viewModel.person.observe(this) { person ->
            initView(person)
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun initView(personDetail: PersonModel) {
        with(binding) {
            nameTextView.text = personDetail.firstName
            surnameTextView.text = personDetail.lastName
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
                Utils.showAlert("Song", personDetail.favorite.song, contex)
            }
            descriptionTextView.setOnClickListener {
                Utils.showAlert("Description", personDetail.description, contex)
            }
            quotaTextView.setOnClickListener {
                Utils.showAlert("Quote", personDetail.quota, contex)
            }
        }
    }
}
