package com.example.domain.models.bank.bankAccount.standartBankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.BankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.user.ClientBank

data class StandardBankAccount(
    override val id: Int,
    override val bank: Bank,
    override val user: ClientBank,
    override val balance: Double,
    override val statusBankAccount: StatusBankAccount
) : BankAccount()
