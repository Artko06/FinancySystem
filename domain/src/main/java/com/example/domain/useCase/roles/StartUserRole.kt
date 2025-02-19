package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertAdminUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertClientUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertCompanyUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertManagerUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertOperatorUserUseCase

interface StartUserRole {
    // GET


    // INSERT
    val insertAdminUserUseCase: InsertAdminUserUseCase
    val insertBaseUserUseCase: InsertBaseUserUseCase
    val insertClientUserUseCase: InsertClientUserUseCase
    val insertCompanyUserUseCase: InsertCompanyUserUseCase
    val insertManagerUserUseCase: InsertManagerUserUseCase
    val insertOperatorUserUseCase: InsertOperatorUserUseCase


    // DELETE

    // CHANGE
}