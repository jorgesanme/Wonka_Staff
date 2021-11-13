package com.example.wonka_staff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wonka_staff.UI.StaffList.StaffViewHolder
import com.example.wonka_staff.databinding.StaffItemBinding
import com.example.wonka_staff.models.Result

class StaffRecycleAdapter : RecyclerView.Adapter<StaffViewHolder>() {


    var staffList: MutableList<Result> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

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
