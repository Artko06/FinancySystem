package com.example.financysystem

import android.app.Application
import com.example.data.local.init.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class FinancialSystemApp : Application() {

    @Inject
    lateinit var databaseInitializer: DatabaseInitializer

    override fun onCreate() {
        super.onCreate()

        // Отложенное наполнение базы данных
        CoroutineScope(Dispatchers.IO).launch {
            databaseInitializer.fillDatabase(appContext = this@FinancialSystemApp)
        }
    }
}