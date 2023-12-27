package com.example.pampraktikum8

import android.app.Application
import com.example.pampraktikum8.repository.AppContainer
import com.example.pampraktikum8.repository.KontakContainer

class KontakApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}