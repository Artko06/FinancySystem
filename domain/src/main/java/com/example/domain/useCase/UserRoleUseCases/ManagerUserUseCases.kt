package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.roles.ManagerUserRole
import com.example.domain.useCase.roles.OperatorUserRole
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

data class ManagerUserUseCases(
    override val getAllTransfersUseCase: GetAllTransfersUseCase,
    override val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase
) : ManagerUserRole, OperatorUserRole