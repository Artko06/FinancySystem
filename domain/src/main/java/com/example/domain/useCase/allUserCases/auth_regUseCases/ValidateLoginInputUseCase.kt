package com.example.domain.useCase.allUserCases.auth_regUseCases

import com.example.domain.models.auth_reg.LoginInputValidationType
import com.example.domain.models.auth_reg.RegisterInputValidationType

class ValidateLoginInputUseCase() {

    operator fun invoke(email: String, password: String): LoginInputValidationType {
        if (email.isBlank() || password.isBlank()) {
            return LoginInputValidationType.EmptyField
        }
        if ("@" !in email || "." !in email) {
            return LoginInputValidationType.IncorrectEmail
        }
        if(password.count() < 8){
            return LoginInputValidationType.PasswordTooShort
        }
        return LoginInputValidationType.Valid
    }

}