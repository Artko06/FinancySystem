package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

interface OperatorUserRole {
    val getAllTransfersUseCase: GetAllTransfersUseCase
}

//override val transfers: List<ITransfer>,
//override val salaryProjectsCompany: List<ISalaryProjectCompany>