package com.trakdesk.app.activities

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.trakdesk.app.databases.AppPreferences

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppPreferences.init(this)
    }
}