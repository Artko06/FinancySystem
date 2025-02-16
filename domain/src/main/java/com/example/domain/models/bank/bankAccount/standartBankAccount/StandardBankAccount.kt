package com.example.domain.models.bank.bankAccount.standartBankAccount

import com.example.domain.models.bank.bankAccount.BaseBankAccount

data class StandardBankAccount(
    val id: Int,
    val baseBankAccount: BaseBankAccount
) : IStandardBankAccount
