package com.trakdesk.app.databases

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "trakdesk_app"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    // list of app specific preferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)
    private val EMAIL = Pair("email", "")
    private val PASSWORD = Pair("password", "")


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit()
    and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(
        operation:
            (SharedPreferences.Editor) -> Unit
    ) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }


    var email: String
        get() = preferences.getString(EMAIL.first, EMAIL.second).toString()
        set(value) = preferences.edit { it.putString(EMAIL.first, value) }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second).toString()
        set(value) = preferences.edit { it.putString(PASSWORD.first, value) }


}