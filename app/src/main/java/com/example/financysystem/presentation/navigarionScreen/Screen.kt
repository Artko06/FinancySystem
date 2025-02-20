package com.example.financysystem.presentation.navigarionScreen

sealed class Screen(
    val route: String
)
{
    object LoginScreen: Screen(route = "LOGIN_SCREEN")
    object RegistrationScreen: Screen(route = "REGISTRATION_SCREEN")
    object UserScreen: Screen(route = "USER_SCREEN")
}