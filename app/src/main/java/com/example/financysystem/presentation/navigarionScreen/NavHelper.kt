package com.example.financysystem.presentation.navigarionScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NavHelper(
    shouldNavigate: () -> Boolean,
    toNavigate: () -> Unit
) {
    LaunchedEffect(key1 = shouldNavigate()) {
        if (shouldNavigate()) {
            toNavigate()
        }
    }
}