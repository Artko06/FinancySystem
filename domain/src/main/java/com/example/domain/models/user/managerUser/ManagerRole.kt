package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.BankAccount

interface ManagerRole {
    val bankAccounts: List<BankAccount>
}