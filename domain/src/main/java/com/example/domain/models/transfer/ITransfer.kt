package com.example.domain.models.transfer

import com.example.domain.models.bank.bankAccount.IBankAccount

interface ITransfer {
    val id: String
    val fromAccount: IBankAccount
    val toAccount: IBankAccount
    val amount: Double
    val status: StatusTransfer
}