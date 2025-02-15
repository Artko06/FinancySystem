package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.BaseBankAccount

interface ManagerRole {
    val baseBankAccounts: List<BaseBankAccount>
}