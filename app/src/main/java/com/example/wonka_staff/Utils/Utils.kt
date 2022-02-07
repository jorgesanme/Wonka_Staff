package com.example.wonka_staff.Utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Utils {
    companion object {
        fun showAlert(title: String, message: String, contex: Context?) {
            val builder = AlertDialog.Builder(contex!!)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("Acept", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    object Constants {
        const val API_HEADER = "?page="
    }
}