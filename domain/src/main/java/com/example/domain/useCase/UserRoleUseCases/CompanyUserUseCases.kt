package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeBalanceBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.roles.CompanyUserRole
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertCompanyBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByCompanyUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.insert.InsertTransferUseCase

data class CompanyUserUseCases(
    // GET
    override val getCompanyBankAccountsByCompanyUseCase: GetCompanyBankAccountsByCompanyUseCase,
    override val getAllBanksUseCases: GetAllBanksUseCases,
    override val getSalaryProjectsByCompanyUseCase: GetSalaryProjectsByCompanyUseCase,

    // INSERT
    override val insertActionLogUseCase: InsertActionLogUseCase,
    override val insertCompanyBankAccountUseCase: InsertCompanyBankAccountUseCase,
    override val insertTransferUseCase: InsertTransferUseCase,

    // DELETE


    // CHANGE
    override val changeBalanceBaseBankAccountUseCase: ChangeBalanceBaseBankAccountUseCase,
    override val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase,
    override val changeStatusSalaryProjectUseCase: ChangeStatusSalaryProjectUseCase
): CompanyUserRole