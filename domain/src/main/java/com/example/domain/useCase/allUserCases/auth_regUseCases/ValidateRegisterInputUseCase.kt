package com.example.domain.useCase.allUserCases.auth_regUseCases

import com.example.domain.models.auth_reg.RegisterInputValidationType

class ValidateRegisterInputUseCase {
    operator fun invoke(
        email: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType {
        if(email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()){
            return RegisterInputValidationType.EmptyField
        }
        if("@" !in email){
            return RegisterInputValidationType.NoEmail
        }
        if(password != passwordRepeated){
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if(password.count() < 8){
            return RegisterInputValidationType.PasswordTooShort
        }
        if(!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if(!password.containsUpperCase()){
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if(!password.containsSpecialChar()){
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        return RegisterInputValidationType.Valid
    }
}

fun String.containsNumber(): Boolean {
    val regex = Regex(".*\\d+.*")
    return regex.matches(this)
}

fun String.containsUpperCase(): Boolean {
    val regex = Regex(".*[A-Z]+.*")
    return regex.matches(this)
}

fun String.containsSpecialChar(): Boolean {
    val regex = Regex(".*[^A-Za-z\\d]+.*")
    return regex.matches(this)
}