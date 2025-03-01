package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeBalanceBankAccount
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllCompanyBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllStandardBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetBaseBankAccountById
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.roles.ManagerUserRole
import com.example.domain.useCase.roles.OperatorUserRole
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.change.ChangeStatusTransferUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetTransferById

data class ManagerUserUseCases(
    // GET
    override val getAllTransfersUseCase: GetAllTransfersUseCase,
    override val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase,
    override val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase,
    override val getBaseUserUseCase: GetBaseUserUseCase,
    override val getTransferById: GetTransferById,
    override val getAllStandardBankAccount: GetAllStandardBankAccountUseCase,
    override val getAllCreditBankAccount: GetAllCreditBankAccountUseCase,
    override val getAllCompanyBankAccount: GetAllCompanyBankAccountUseCase,
    override val getBaseBankAccountById: GetBaseBankAccountById,


    // INSERT
    override val insertActionLogUseCase: InsertActionLogUseCase,


    // CHANGE
    override val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase,
    override val changeStatusCreditBankAccountUseCase: ChangeStatusCreditBankAccountUseCase,
    override val changeStatusTransferUseCase: ChangeStatusTransferUseCase,
    override val changeBalanceBankAccount: ChangeBalanceBankAccount,
    override val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase,
): ManagerUserRole, OperatorUserRole