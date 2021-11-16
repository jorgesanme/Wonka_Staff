package com.example.wonka_staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.wonka_staff.Utils.Utils
import com.example.wonka_staff.databinding.ActivityMainBinding
import com.example.wonka_staff.models.Result
import com.example.wonka_staff.viewModel.StaffViewModel

enum class genders(val letter: String) {
    Female("F"),
    Male("M"),
    Both("")
}

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: StaffViewModel
    var page: Int = 1
    var gender: String = genders.Both.letter
    var query: String? = ""
    val adapter = StaffRecycleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
        }
        viewModel = ViewModelProvider(this).get(StaffViewModel::class.java)

        viewModel.getStaffList(page, gender, query)

        /** Page Selector**/
        binding.btNextPage.setOnClickListener {
            if (page < 20) {
                page++
                binding.page.text = page.toString()
            } else {
                Utils.showAlert(
                    "Pay Atention",
                    "You have arrived to the last page",
                    this
                )
            }
            viewModel.getStaffList(page, gender, query)
        }
        binding.btPreviusPage.setOnClickListener {
            if (page > 1) {
                page--
                binding.page.text = page.toString()
            } else {
                Utils.showAlert(
                    "Pay Atention",
                    "There is no more previous page",
                    this
                )
            }
            viewModel.getStaffList(page, gender, query)
        }

        /** Gender Selector**/
        binding.btFemale.setOnClickListener {
            gender = genders.Female.letter
            viewModel.getStaffList(page, gender, query )
        }

        binding.btMale.setOnClickListener {
            gender = genders.Male.letter
            viewModel.getStaffList(page, gender, query )
        }

        binding.btAllGende.setOnClickListener {
            gender = genders.Both.letter
            viewModel.getStaffList(page, gender, query )
        }

        /** search*/
        binding.searchBar.setOnQueryTextListener(this)

        /** Filter by gender*/
        viewModel.state.observe(this) { state ->
            when (gender) {
                genders.Female.letter -> adapter.staffList =
                    state.staffList.filter { it.gender.equals(gender) } as MutableList<Result>
                genders.Male.letter -> adapter.staffList =
                    state.staffList.filter { it.gender.equals(gender) } as MutableList<Result>
                genders.Both.letter -> adapter.staffList = state.staffList as MutableList<Result>
            }
        }
        binding.staffRV.adapter = adapter
    }
    private fun hideKeyboard(){
        val imm= getSystemService(INPUT_METHOD_SERVICE)as InputMethodManager
        imm.hideSoftInputFromWindow(binding.rootViewRV.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        this.query = query
        if (!query.isNullOrEmpty()) {
            viewModel.getStaffList(page, gender, query)
        }
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        this.query = query
        if (!query.isNullOrEmpty()) {
            viewModel.getStaffList(page, gender, query)
        }
        return true
    }

}



