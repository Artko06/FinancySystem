package com.example.financysystem.presentation.screens.loginRegistrScreen.event

sealed class RegistrationEvent {
    data class onEmailInputChange(val newValue: String): RegistrationEvent()
    data class onPasswordInputChange(val newValue: String): RegistrationEvent()
    data class onPasswordRepeatedInputChange(val newValue: String): RegistrationEvent()
    data class onPhoneInputChange(val newValue: String): RegistrationEvent()
    data class onFirstNameInputChange(val newValue: String): RegistrationEvent()
    data class onLastNameInputChange(val newValue: String): RegistrationEvent()
    data class onSurNameInputChange(val newValue: String): RegistrationEvent()
    data class onSeriesPassportInputChange(val newValue: String): RegistrationEvent()
    data class onNumberPassportInputChange(val newValue: String): RegistrationEvent()
    data class onIdentityNumberInputChange(val newValue: String): RegistrationEvent()
    object onToggleVisualTransformationPassword: RegistrationEvent()
    object onToggleVisualTransformationPasswordRepeated: RegistrationEvent()
    object onRegisterClick: RegistrationEvent()
}