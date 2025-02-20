package com.example.financysystem.presentation.screens.loginRegistrScreen.state

data class RegistrationState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val passwordRepeatedInput: String = "",
    val typeOfUser: TypeOfUser = TypeOfUser.ClientUser,
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val isPasswordRepeatedShown: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegistered: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)