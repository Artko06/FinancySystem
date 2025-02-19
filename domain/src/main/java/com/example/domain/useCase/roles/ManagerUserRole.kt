package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

interface ManagerUserRole {
    val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase
    val getAllTransfersUseCase: GetAllTransfersUseCase
}