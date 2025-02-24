package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertAdminUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertClientUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertCompanyUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertManagerUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertOperatorUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.other.ValidateEmailUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.other.ValidatePasswordUseCase
import com.example.domain.useCase.allUserCases.auth_regUseCases.ValidateLoginInputUseCase
import com.example.domain.useCase.allUserCases.auth_regUseCases.ValidateRegisterInputUseCase
import com.example.domain.useCase.roles.StartUserRole

data class StartUserUseCases(
    // GET
    override val getBaseUserUseCase: GetBaseUserUseCase,



    // INSERT
    override val insertAdminUserUseCase: InsertAdminUserUseCase,
    override val insertClientUserUseCase: InsertClientUserUseCase,
    override val insertCompanyUserUseCase: InsertCompanyUserUseCase,
    override val insertManagerUserUseCase: InsertManagerUserUseCase,
    override val insertOperatorUserUseCase: InsertOperatorUserUseCase,

    // DELETE


    // CHANGE


    // OTHER
    override val validateEmailUseCase: ValidateEmailUseCase,
    override val validatePasswordUseCase: ValidatePasswordUseCase,
    override val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    override val validateLoginInputUseCase: ValidateLoginInputUseCase,
): StartUserRole