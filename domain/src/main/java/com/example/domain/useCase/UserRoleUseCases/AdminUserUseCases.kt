package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.roles.AdminUserRole
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase

data class AdminUserUseCases(
    override val getAllActionLogsUseCase: GetAllActionLogsUseCase
) : AdminUserRole