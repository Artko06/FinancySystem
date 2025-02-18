package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase

interface AdminUserRole {
    val getAllActionLogsUseCase: GetAllActionLogsUseCase
}