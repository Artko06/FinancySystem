package com.example.domain.models.user

import com.example.domain.models.bank.bankAccount.BankAccount

interface ClientBank {
    val bankAccounts: List<BankAccount>
}