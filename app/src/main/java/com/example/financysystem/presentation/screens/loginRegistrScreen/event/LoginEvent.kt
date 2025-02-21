package com.example.financysystem.presentation.screens.loginRegistrScreen.event

import com.example.financysystem.presentation.screens.loginRegistrScreen.state.TypeOfUser

sealed class LoginEvent {
    data class onEmailInputChange(val newValue: String): LoginEvent()
    data class onPasswordInputChange(val newValue: String): LoginEvent()
    data class onTypeOfUserInputChange(val typeOfUser: TypeOfUser): LoginEvent()
    object onToggleVisualTransformation: LoginEvent()
    object onLoginClick: LoginEvent()
}