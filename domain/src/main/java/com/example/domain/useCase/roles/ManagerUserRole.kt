package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllCompanyBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllStandardBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetBaseBankAccountById
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

interface ManagerUserRole {
    // GET
    val getAllSalaryProjectUseCase: GetAllSalaryProjectUseCase
    val getAllTransfersUseCase: GetAllTransfersUseCase
    val getBaseUserUseCase: GetBaseUserUseCase
    val getAllStandardBankAccount: GetAllStandardBankAccountUseCase
    val getAllCreditBankAccount: GetAllCreditBankAccountUseCase
    val getAllCompanyBankAccount: GetAllCompanyBankAccountUseCase
    val getBaseBankAccountById: GetBaseBankAccountById


    // INSERT
    val insertActionLogUseCase: InsertActionLogUseCase


    // CHANGE
    val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase
    val changeStatusCreditBankAccountUseCase: ChangeStatusCreditBankAccountUseCase
}