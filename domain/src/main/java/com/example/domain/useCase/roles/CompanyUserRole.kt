package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.get.GetCompanyUserByBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByCompanyUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert.InsertSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.other.CreateTransferUseCase

interface CompanyUserRole {
    // GET
    val getCompanyBankAccountsByCompanyUseCase: GetCompanyBankAccountsByCompanyUseCase
    val getAllBanksUseCases: GetAllBanksUseCases
    val getSalaryProjectsByCompanyUseCase: GetSalaryProjectsByCompanyUseCase
    val getBaseUserUseCase: GetBaseUserUseCase
    val getCompanyUserByBaseUserUseCase: GetCompanyUserByBaseUserUseCase


    // INSERT
    val insertActionLogUseCase: InsertActionLogUseCase
    val insertSalaryProjectUseCase: InsertSalaryProjectUseCase


    //CHANGE
    val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase
    val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase


    // OTHER
    val createTransferUseCase: CreateTransferUseCase
}