package com.example.domain.models.bank.bankAccount.companyBankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.company.Company
import com.example.domain.models.user.clientUser.ClientUser

data class CompanyBankAccount(
    val company: Company,

    override val id: String,
    override val bank: Bank,
    override val user: ClientUser,
    override val balance: Double,
    override val statusBankAccount: StatusBankAccount
) : IBankAccount
