package com.example.wonka_staff

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wonka_staff.databinding.StaffItemBinding
import com.example.wonka_staff.models.testModel
import kotlinx.coroutines.*
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

        val job = CoroutineScope(Dispatchers.Main).launch {
            val bitmap = downloadImage(person.image)
            withContext(Dispatchers.Main) {
                        binding.avatarView.setImageBitmap(bitmap)
            }
        }

    }
    private suspend fun downloadImage(url: String): Bitmap{
        return withContext(Dispatchers.IO){
            val okHttpClient = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = okHttpClient.newCall(request).execute()
            val bitmap = BitmapFactory.decodeStream(response.body!!.byteStream())
            bitmap
        }

    }
}