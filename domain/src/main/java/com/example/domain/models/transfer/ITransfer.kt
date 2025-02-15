package com.example.domain.models.transfer

import com.example.domain.models.bank.bankAccount.BaseBankAccount

interface ITransfer {
    val id: Int
    val fromBaseBankAccount: BaseBankAccount
    val toBaseBankAccount: BaseBankAccount
    val amount: Double
    val dateTransfer: String
    val timeTransfer: String
    val status: StatusTransfer
}