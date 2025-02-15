package com.example.domain.models.user.companyUser

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.CompanyClientBank

data class CompanyUser(
    val baseUser: BaseUser,
    override val company: Company,
    override val companyBankAccounts: List<ICompanyBankAccount>
) : CompanyRole, CompanyClientBank
