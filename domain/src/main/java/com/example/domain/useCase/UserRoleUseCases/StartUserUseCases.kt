package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertAdminUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertClientUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertCompanyUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertManagerUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertOperatorUserUseCase
import com.example.domain.useCase.roles.StartUserRole

data class StartUserUseCases(
    // GET


    // INSERT
    override val insertAdminUserUseCase: InsertAdminUserUseCase,
    override val insertBaseUserUseCase: InsertBaseUserUseCase,
    override val insertClientUserUseCase: InsertClientUserUseCase,
    override val insertCompanyUserUseCase: InsertCompanyUserUseCase,
    override val insertManagerUserUseCase: InsertManagerUserUseCase,
    override val insertOperatorUserUseCase: InsertOperatorUserUseCase

    // DELETE


    // CHANGE
): StartUserRole