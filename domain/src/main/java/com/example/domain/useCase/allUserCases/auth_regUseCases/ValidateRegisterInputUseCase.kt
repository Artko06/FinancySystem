package com.example.domain.useCase.allUserCases.auth_regUseCases

import com.example.domain.models.auth_reg.RegisterInputValidationType

class ValidateRegisterInputUseCase {
    operator fun invoke(
        firstName: String,
        lastName: String,
        surName: String,
        seriesPassport: String,
        numberPassport: String,
        identityNumber: String,
        phone: String,
        email: String,
        password: String,
        passwordRepeated: String,

    ): RegisterInputValidationType {
        if(email.isBlank()
            || password.isBlank()
            || passwordRepeated.isBlank()
            || firstName.isBlank()
            || lastName.isBlank()
            || surName.isBlank()
            || seriesPassport.isBlank()
            || numberPassport.isBlank()
            || identityNumber.isBlank()
            || phone.isBlank())
        {
            return RegisterInputValidationType.EmptyField
        }
        if("@" !in email || "." !in email){
            return RegisterInputValidationType.IncorrectEmail
        }
        if("+" !in phone){
            return RegisterInputValidationType.IncorrectPhone
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
        if(!password.containsLowerCase()){
            return RegisterInputValidationType.PasswordLowerCaseMissing
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

fun String.containsLowerCase(): Boolean {
    val regex = Regex(".*[a-z]+.*")
    return regex.matches(this)
}