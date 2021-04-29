package com.trakdesk.app.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.trakdesk.app.R
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

internal object Utility {
    private val tag = Utility::class.java.simpleName

    private var isOnline = false

    fun isNullOrEmpty(str: String?): Boolean {
        return !(str != null && str.isNotEmpty())
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        var online = false
        if (context != null) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting)
                online = isOnline()
        }
        return online
    }

    private fun isOnline(): Boolean {

        Thread {
            try {
                isOnline = false
                val url = URL("https://www.google.com")
                val connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 60000
                connection.connect()
                isOnline = connection.responseCode == 200
                Log.d(tag, "isOnline: reachable: $isOnline")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
        Log.d(tag, "isOnline: reachable: $isOnline")
        return isOnline
    }


    fun showMessage(context: Context, msg: String) {
        try {
            (context as Activity).runOnUiThread {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle(R.string.app_name)
                dialog.setCancelable(true)
                dialog.setMessage(msg)
                dialog.setPositiveButton(R.string.str_ok,
                    DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
                val mAlertDialog = dialog.create()
                if (!context.isFinishing) {
                    mAlertDialog.show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}