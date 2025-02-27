package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetBaseBankAccountById
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetStandardBankAccountsByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertStandardBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.companyUseCases.get.GetAllCompanyUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByClientBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert.InsertSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.other.CreateTransferUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.other.validateTransfer.ValidateTransferUseCase

interface ClientUserRole {
    // GET
    val getStandardBankAccountsByBaseUserUseCase: GetStandardBankAccountsByBaseUserUseCase
    val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase
    val getSalaryProjectsByClientBaseUserUseCase: GetSalaryProjectsByClientBaseUserUseCase
    val getAllBanksUseCases: GetAllBanksUseCases
    val getAllCompanyUseCase: GetAllCompanyUseCase
    val getBaseUserUseCase: GetBaseUserUseCase
    val getBaseBankAccountById: GetBaseBankAccountById

    // INSERT
    val insertActionLogUseCase: InsertActionLogUseCase
    val insertCreditBankAccountUseCase: InsertCreditBankAccountUseCase
    val insertStandardBankAccountUseCase: InsertStandardBankAccountUseCase
    val insertSalaryProjectUseCase: InsertSalaryProjectUseCase


    // CHANGE
    val changeBalanceBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase
    val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase


    // OTHER
    val createTransferUseCase: CreateTransferUseCase
    val validateTransferUseCase: ValidateTransferUseCase
}