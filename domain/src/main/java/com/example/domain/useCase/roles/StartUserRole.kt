package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertAdminUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertClientUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertCompanyUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertManagerUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertOperatorUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.other.ValidateEmailUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.other.ValidatePasswordUseCase
import com.example.domain.useCase.allUserCases.auth_regUseCases.ValidateLoginInputUseCase
import com.example.domain.useCase.allUserCases.auth_regUseCases.ValidateRegisterInputUseCase

interface StartUserRole {
    // GET


    // INSERT
    val insertAdminUserUseCase: InsertAdminUserUseCase
    val insertClientUserUseCase: InsertClientUserUseCase
    val insertCompanyUserUseCase: InsertCompanyUserUseCase
    val insertManagerUserUseCase: InsertManagerUserUseCase
    val insertOperatorUserUseCase: InsertOperatorUserUseCase


    // DELETE

    // CHANGE


    // OTHER
    val validateEmailUseCase: ValidateEmailUseCase
    val validatePasswordUseCase: ValidatePasswordUseCase
    val validateRegisterInputUseCase: ValidateRegisterInputUseCase
    val validateLoginInputUseCase: ValidateLoginInputUseCase
}