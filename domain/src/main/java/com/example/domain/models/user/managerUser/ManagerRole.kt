package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.IBankAccount

interface ManagerRole {
    val idCreditsAccount: List<IBankAccount>
}