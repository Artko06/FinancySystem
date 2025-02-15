package com.example.domain.models.user.companyUser

import com.example.domain.models.bank.bankAccount.BankAccount
import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.ClientBank

data class CompanyUser(
    val baseUser: BaseUser,
    override val company: Company,
    override val bankAccounts: List<BankAccount>
) : CompanyRole, ClientBank
