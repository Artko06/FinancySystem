package com.example.financysystem.presentation.screens.loginRegistrScreen.state

data class LoginState(
    val email: String = "",
    val password: String = "",
    val typeOfUser: TypeOfUser = TypeOfUser.ClientUser,
    val isInputValid: Boolean = false,
    val isLoading: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMessageInput: String? = null,
    val isSuccessfullyLoggedIn: Boolean = false,
)
