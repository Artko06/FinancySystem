package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeBalanceBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertCompanyBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByCompanyUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.insert.InsertTransferUseCase

interface CompanyUserRole {
    // GET
    val getCompanyBankAccountsByCompanyUseCase: GetCompanyBankAccountsByCompanyUseCase
    val getAllBanksUseCases: GetAllBanksUseCases
    val getSalaryProjectsByCompanyUseCase: GetSalaryProjectsByCompanyUseCase

    // INSERT
    val insertActionLogUseCase: InsertActionLogUseCase
    val insertCompanyBankAccountUseCase: InsertCompanyBankAccountUseCase
    val insertTransferUseCase: InsertTransferUseCase

    //DELETE


    //CHANGE
    val changeBalanceBaseBankAccountUseCase: ChangeBalanceBaseBankAccountUseCase
    val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase
    val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase
}