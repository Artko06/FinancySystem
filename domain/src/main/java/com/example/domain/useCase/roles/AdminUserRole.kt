package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase

interface AdminUserRole {
    // GET
    val getAllActionLogsUseCase: GetAllActionLogsUseCase
    val getBaseUserUseCase: GetBaseUserUseCase

    // INSERT


    // DELETE


    // CHANGE
}