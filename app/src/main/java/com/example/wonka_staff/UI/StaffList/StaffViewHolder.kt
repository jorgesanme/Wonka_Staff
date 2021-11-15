package com.example.wonka_staff.UI.StaffList

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wonka_staff.databinding.StaffItemBinding
import com.example.wonka_staff.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class StaffViewHolder(val binding: StaffItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(person: Result) {
        with(binding) {
            fistName.text = person.firstName
            lastName.text = person.lastName
            gender.text = person.gender
            job.text = person.profession
            id.text = person.id.toString()
            avatarView.setImageBitmap(null)

            val job = CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    Glide.with(root).load(person.image).also {
                        it.circleCrop()
                    }.into(avatarView)
                }
            }
            itemCard.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailPerson::class.java)
                intent.putExtra("personId", person.id.toString())
                context.startActivity(intent)
            }
        }

    }

}


