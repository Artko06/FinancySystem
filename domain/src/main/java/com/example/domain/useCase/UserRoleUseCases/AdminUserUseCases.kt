package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.roles.AdminUserRole
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase

data class AdminUserUseCases(
    // GET
    override val getAllActionLogsUseCase: GetAllActionLogsUseCase

    // INSERT


    // DELETE


    // CHANGE
): AdminUserRole