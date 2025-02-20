package com.example.domain.models.bank.bankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.user.BaseUser

data class BaseBankAccount(
    val id: Int,
    //val email: String,
    //val password: String, не должно быть, только в data
    val bank: Bank,
    val baseUser: BaseUser,
    val balance: Double,
    val statusBankAccount: StatusBankAccount
)
