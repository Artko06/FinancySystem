package com.example.domain.models.user

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount

interface DefaultClientBank {
    val standardBankAccounts: List<IStandardBankAccount>
    val creditBankAccounts: List<ICreditBankAccount>
}