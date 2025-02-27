package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.get.GetCompanyUserByBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertCompanyBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByCompanyUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByStatus
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert.InsertSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.other.CreateTransferUseCase
import com.example.domain.useCase.roles.CompanyUserRole

data class CompanyUserUseCases(
    // GET
    override val getCompanyBankAccountsByCompanyUseCase: GetCompanyBankAccountsByCompanyUseCase,
    override val getAllBanksUseCases: GetAllBanksUseCases,
    override val getSalaryProjectsByCompanyUseCase: GetSalaryProjectsByCompanyUseCase,
    override val getBaseUserUseCase: GetBaseUserUseCase,
    override val getSalaryProjectsByStatus: GetSalaryProjectsByStatus,
    override val getCompanyUserByBaseUserUseCase: GetCompanyUserByBaseUserUseCase,



    // INSERT
    override val insertActionLogUseCase: InsertActionLogUseCase,
    override val insertCompanyBankAccountUseCase: InsertCompanyBankAccountUseCase,
    override val insertSalaryProjectUseCase: InsertSalaryProjectUseCase,


    // CHANGE
    override val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase,
    override val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase,


    // OTHER
    override val createTransferUseCase: CreateTransferUseCase,
): CompanyUserRole