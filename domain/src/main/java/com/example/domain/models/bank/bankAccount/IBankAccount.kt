package com.example.domain.models.bank.bankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.user.ClientBank

interface IBankAccount {
    val id: String
    val bank: Bank
    val user: ClientBank
    val balance: Double
    val statusBankAccount : StatusBankAccount
}