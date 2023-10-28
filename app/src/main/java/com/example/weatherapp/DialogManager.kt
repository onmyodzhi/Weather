package com.example.weatherapp

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import androidx.core.graphics.createBitmap

object DialogManager {
    fun locationSettingsDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Enable location?")
        dialog.setMessage("Location is disabled.\n" +
                "Do you wont enable location?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes"){ _,_ ->
            listener.onClick(null)
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"){ _,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    fun searchByNameDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val editCityName = EditText(context)
        builder.setView(editCityName)
        val dialog = builder.create()
        dialog.setTitle("Write down desired location: ")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok"){ _,_ ->
            listener.onClick(editCityName.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel"){ _,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    interface Listener{
        fun onClick(name:String?)
    }
}