package com.example.financysystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.financysystem.presentation.navigarionScreen.NavigationScreen
import com.example.financysystem.ui.theme.FinancySystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancySystemTheme {
//                deleteDatabase(FinancialDataBase.DATABASE_NAME)
                NavigationScreen()
            }
        }
    }
}