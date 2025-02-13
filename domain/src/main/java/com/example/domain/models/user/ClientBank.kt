package com.example.domain.models.user

import com.example.domain.models.bank.bankAccount.IBankAccount

interface ClientBank {
    val bankAccounts: List<IBankAccount>
}