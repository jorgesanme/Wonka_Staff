package com.example.wonka_staff

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wonka_staff.databinding.StaffItemBinding
import com.example.wonka_staff.models.testModel
import okhttp3.OkHttpClient
import okhttp3.Request

class StaffRecycleAdapter : RecyclerView.Adapter<StaffViewHolder>() {

    //todo cambiar list por MVVM->list
    var staffList: List<testModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder =
        StaffItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run {
            StaffViewHolder(this)
        }


    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val person = staffList[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = staffList.size
}

data class StaffViewHolder(val binding: StaffItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(person: testModel){
        binding.fistName.text  = person.firstName
        binding.lastName.text = person.lastName
        binding.gender.text = person.gender

        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(person.image).build()

        val response = okHttpClient.newCall(request).execute()

        val bitmap = BitmapFactory.decodeStream(response.body!!.byteStream())
        binding.avatarView.setImageBitmap(bitmap)

    }
}