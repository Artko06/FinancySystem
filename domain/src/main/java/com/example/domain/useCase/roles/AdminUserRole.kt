package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase

interface AdminUserRole {
    // GET
    val getAllActionLogsUseCase: GetAllActionLogsUseCase
    val getBaseUserUseCase: GetBaseUserUseCase

    // INSERT
    val insertActionLogUseCase: InsertActionLogUseCase


    // CHANGE
}