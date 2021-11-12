package com.example.wonka_staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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

        binding.btNextPage.setOnClickListener {
            if (page < 20) {
                page++
                binding.page.text = page.toString()
            }else{
                Toast.makeText(binding.root.context,"Last page: ${page.toString()}", Toast.LENGTH_LONG).show()

            }
        }
        binding.btPreviusPage.setOnClickListener {
            if (page > 1) {
                page--
                binding.page.text = page.toString()
            }else{
                Toast.makeText(binding.root.context,"First page: ${page.toString()}", Toast.LENGTH_LONG).show()
            }
        }

        val viewModel = ViewModelProvider(this).get(StaffViewModel::class.java)
        val adapter = StaffRecycleAdapter()
        viewModel.getStaffList(page)
        viewModel.state.observe(this){ state ->
            adapter.staffList = state.staffList as MutableList<Result>
        }

        binding.staffRV.adapter = adapter

    }

}



