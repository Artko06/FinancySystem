package com.example.domain.models.auth_reg

enum class RegisterInputValidationType {
    EmptyField,
    NoEmail,
    IncorrectPhone,
    PasswordsDoNotMatch,
    PasswordUpperCaseMissing,
    PasswordNumberMissing,
    PasswordSpecialCharMissing,
    PasswordTooShort,
    Valid
}