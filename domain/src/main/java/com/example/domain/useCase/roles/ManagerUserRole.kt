package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

interface ManagerUserRole {
    // GET
    val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase
    val getAllTransfersUseCase: GetAllTransfersUseCase
    val getBaseUserUseCase: GetBaseUserUseCase


    // INSERT


    // DELETE


    // CHANGE
}