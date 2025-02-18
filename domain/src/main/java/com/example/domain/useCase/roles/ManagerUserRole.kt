package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase

interface ManagerUserRole {
    val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase
}

//override val transfers: List<ITransfer>,
//override val salaryProjectsCompany: List<ISalaryProjectCompany>,
//override val baseBankAccounts: List<BaseBankAccount>