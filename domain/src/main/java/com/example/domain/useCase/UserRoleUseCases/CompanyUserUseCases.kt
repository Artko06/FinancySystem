package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.roles.CompanyUserRole
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase

data class CompanyUserUseCases(
    override val getCompanyBankAccountsByCompanyUseCase: GetCompanyBankAccountsByCompanyUseCase
) : CompanyUserRole