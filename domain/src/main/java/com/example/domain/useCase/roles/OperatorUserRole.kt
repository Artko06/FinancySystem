package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeBalanceBankAccount
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.change.ChangeStatusTransferUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetTransferById

interface OperatorUserRole {
    // GET
    val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase
    val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase
    val getAllTransfersUseCase: GetAllTransfersUseCase
    val getBaseUserUseCase: GetBaseUserUseCase
    val getTransferById: GetTransferById

    // INSERT


    // CHANGE
    val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase
    val changeStatusTransferUseCase: ChangeStatusTransferUseCase
    val changeBalanceBankAccount: ChangeBalanceBankAccount
}