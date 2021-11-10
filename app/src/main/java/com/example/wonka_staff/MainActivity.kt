package com.example.wonka_staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wonka_staff.databinding.ActivityMainBinding
import com.example.wonka_staff.models.StaffModel
import com.example.wonka_staff.models.testModel

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
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg" ,
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
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/3.jpg" ,
            ),
        )
    }
}