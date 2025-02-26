package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetBaseBankAccountById
import com.example.domain.useCase.roles.ClientUserRole
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetStandardBankAccountsByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertStandardBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.companyUseCases.get.GetAllCompanyUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByClientBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert.InsertSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.insert.InsertTransferUseCase

class ClientUserUseCases(
    // GET
    override val getStandardBankAccountsByBaseUserUseCase: GetStandardBankAccountsByBaseUserUseCase,
    override  val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase,
    override val getSalaryProjectsByClientBaseUserUseCase: GetSalaryProjectsByClientBaseUserUseCase,
    override val getAllBanksUseCases: GetAllBanksUseCases,
    override val getAllCompanyUseCase: GetAllCompanyUseCase,
    override val getBaseUserUseCase: GetBaseUserUseCase,
    override val getBaseBankAccountById: GetBaseBankAccountById,


    // INSERT
    override val insertActionLogUseCase: InsertActionLogUseCase,
    override val insertCreditBankAccountUseCase: InsertCreditBankAccountUseCase,
    override val insertStandardBankAccountUseCase: InsertStandardBankAccountUseCase,
    override val insertSalaryProjectUseCase: InsertSalaryProjectUseCase,
    override val insertTransferUseCase: InsertTransferUseCase,

    //DELETE


    // CHANGE
    override val changeBalanceBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase,
    override val changeStatusBaseBankAccountUseCase: ChangeStatusBaseBankAccountUseCase,
): ClientUserRole
