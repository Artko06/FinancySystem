package com.example.domain.models.bank.bankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.DefaultClientBank

data class BaseBankAccount(
    val id: Int,
    val bank: Bank,
    val baseUser: BaseUser,
    val balance: Double,
    val statusBankAccount: StatusBankAccount
)
