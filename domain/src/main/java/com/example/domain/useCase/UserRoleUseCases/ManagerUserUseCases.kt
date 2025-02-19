package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.roles.ManagerUserRole
import com.example.domain.useCase.roles.OperatorUserRole
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

data class ManagerUserUseCases(
    // GET
    override val getAllTransfersUseCase: GetAllTransfersUseCase,
    override val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase,
    override val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase,

    // INSERT


    // DELETE


    // CHANGE
    override val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase,
    override val changeStatusCreditBankAccountUseCase: ChangeStatusCreditBankAccountUseCase
): ManagerUserRole, OperatorUserRole