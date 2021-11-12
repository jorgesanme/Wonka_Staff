package com.example.wonka_staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import com.example.wonka_staff.Utils.Utils
import com.example.wonka_staff.databinding.ActivityMainBinding
import com.example.wonka_staff.models.Result
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.example.wonka_staff.viewModel.StaffViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.internal.notifyAll
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration

class MainActivity : AppCompatActivity() {
    var page: Int = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
        }
        val viewModel = ViewModelProvider(this).get(StaffViewModel::class.java)
        val adapter = StaffRecycleAdapter()
        viewModel.getStaffList(page)

        binding.btNextPage.setOnClickListener {
            if (page < 20) {
                page++
                binding.page.text = page.toString()
            }else{
                Toast.makeText(binding.root.context,"Last page: ${page.toString()}", Toast.LENGTH_LONG).show()
                Utils.showAlert("Pay Atention", "You have arrive to the last page", binding.root.context)

            }
            viewModel.getStaffList(page)
        }
        binding.btPreviusPage.setOnClickListener {
            if (page > 1) {
                page--
                binding.page.text = page.toString()
            }else{
                Toast.makeText(binding.root.context,"First page: ${page.toString()}", Toast.LENGTH_LONG).show()
                Utils.showAlert("Pay Atention", "There is no more previous page", binding.root.context)
            }
            viewModel.getStaffList(page)
        }
        binding.btFemale.setOnClickListener {
            viewModel.getGenderFilterStaffList(page,"F")
        }
        binding.btMale.setOnClickListener {
            viewModel.getGenderFilterStaffList(page,"M")
        }
        binding.btAllGende.setOnClickListener {
            viewModel.getStaffList(page)
        }


        viewModel.state.observe(this){ state ->
            adapter.staffList = state.staffList as MutableList<Result>
        }

        binding.staffRV.adapter = adapter

    }

}



