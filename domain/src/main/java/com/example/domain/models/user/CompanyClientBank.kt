package com.example.domain.models.user

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount

interface CompanyClientBank {
    val companyBankAccounts: List<ICompanyBankAccount>
}