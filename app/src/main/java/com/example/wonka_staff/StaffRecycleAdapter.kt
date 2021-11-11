package com.example.wonka_staff

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wonka_staff.databinding.StaffItemBinding
import com.example.wonka_staff.models.Result
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

class StaffRecycleAdapter : RecyclerView.Adapter<StaffViewHolder>() {

    //todo cambiar list por MVVM->list
    var staffList: List<Result> = emptyList()

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
    fun bind(person: Result){
        with(binding){
            fistName.text  = person.first_name
            lastName.text = person.last_name
            gender.text = person.gender
            job.text = person.profession
            id.text = person.id.toString()
            avatarView.setImageBitmap(null)

            val job = CoroutineScope(Dispatchers.Main).launch {
//                val bitmap = downloadImage(person.image)
                withContext(Dispatchers.Main) {
                    Glide.with(root).load(person.image).also {
                        it.circleCrop()
                    }.into(avatarView)
//                        avatarView.setImageBitmap(bitmap)
                }
            }
        }


    }
    //Not necessary is replaced by glide
//    private suspend fun downloadImage(url: String): Bitmap{
//        return withContext(Dispatchers.IO){
//            val okHttpClient = OkHttpClient()
//            val request = Request.Builder().url(url).build()
//            val response = okHttpClient.newCall(request).execute()
//            val bitmap = BitmapFactory.decodeStream(response.body!!.byteStream())
//            bitmap
//        }
//
//    }
}