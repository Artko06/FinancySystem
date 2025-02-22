package com.example.domain.models.auth_reg

enum class RegisterInputValidationType {
    EmptyField,
    IncorrectEmail,
    IncorrectPhone,
    PasswordsDoNotMatch,
    PasswordLowerCaseMissing,
    PasswordUpperCaseMissing,
    PasswordNumberMissing,
    PasswordTooShort,
    Valid
}