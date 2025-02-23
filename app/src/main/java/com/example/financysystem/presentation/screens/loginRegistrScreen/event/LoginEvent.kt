package com.example.financysystem.presentation.screens.loginRegistrScreen.event

sealed class LoginEvent {
    data class onEmailInputChange(val newValue: String): LoginEvent()
    data class onPasswordInputChange(val newValue: String): LoginEvent()
    object onToggleVisualTransformation: LoginEvent()
    object onLoginClick: LoginEvent()
}