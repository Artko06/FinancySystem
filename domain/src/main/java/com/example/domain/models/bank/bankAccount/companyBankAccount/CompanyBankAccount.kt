package com.example.domain.models.bank.bankAccount.companyBankAccount

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.company.Company

data class CompanyBankAccount(
    val baseBankAccount: BaseBankAccount,
    override val company: Company
) : ICompanyBankAccount
