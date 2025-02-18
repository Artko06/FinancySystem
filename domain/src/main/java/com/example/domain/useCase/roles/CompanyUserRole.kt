package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase

interface CompanyUserRole {
    val getCompanyBankAccountsByCompanyUseCase: GetCompanyBankAccountsByCompanyUseCase
}