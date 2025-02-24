package com.example.financysystem.presentation.navigarionScreen

sealed class Screen(
    val route: String
)
{
    object LoginScreen: Screen(route = "LOGIN_SCREEN")
    object RegistrationScreen: Screen(route = "REGISTRATION_SCREEN")

    object AdminUserScreen: Screen(route = "ADMIN_USER_SCREEN")
    object ClientUserScreen: Screen(route = "CLIENT_USER_SCREEN")
    object CompanyUserScreen: Screen(route = "COMPANY_USER_SCREEN")
    object ManagerUserScreen: Screen(route = "MANAGER_USER_SCREEN")
    object OperatorUserScreen: Screen(route = "OPERATOR_USER_SCREEN")
}