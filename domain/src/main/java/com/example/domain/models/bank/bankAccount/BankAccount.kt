package com.example.domain.models.bank.bankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.user.ClientBank

abstract class BankAccount{
    abstract val id: Int
    abstract val bank: Bank
    abstract val user: ClientBank
    abstract val balance: Double
    abstract val statusBankAccount: StatusBankAccount
}