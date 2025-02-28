package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeBalanceBankAccount
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.change.ChangeStatusTransferUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetTransferById
import com.example.domain.useCase.roles.OperatorUserRole

data class OperatorUserUseCases(
    // GET
    override val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase,
    override val getAllTransfersUseCase: GetAllTransfersUseCase,
    override val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase,
    override val getBaseUserUseCase: GetBaseUserUseCase,
    override val getTransferById: GetTransferById,


    // INSERT


    // CHANGE
    override val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase,
    override val changeStatusTransferUseCase: ChangeStatusTransferUseCase,
    override val changeBalanceBankAccount: ChangeBalanceBankAccount,
): OperatorUserRole