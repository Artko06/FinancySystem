package com.example.domain.useCase.allUserCases.auth_regUseCases

import com.example.domain.models.auth_reg.LoginInputValidationType

class ValidateLoginInputUseCase() {

    operator fun invoke(email: String, password: String): LoginInputValidationType {
        if (email.isEmpty() || password.isEmpty()) {
            return LoginInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }

}