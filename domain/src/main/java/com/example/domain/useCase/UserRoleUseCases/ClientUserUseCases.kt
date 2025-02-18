package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.roles.ClientUserRole
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetStandardBankAccountsByBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByClientBaseUserUseCase

class ClientUserUseCases(
    override val getStandardBankAccountsByBaseUserUseCase: GetStandardBankAccountsByBaseUserUseCase,
    override val getCreditBankAccountByBaseUserUseCase: GetCreditBankAccountByBaseUserUseCase,
    override val getSalaryProjectsByClientBaseUserUseCase: GetSalaryProjectsByClientBaseUserUseCase
) : ClientUserRole
