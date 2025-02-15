package com.example.domain.models.transfer

import com.example.domain.models.bank.bankAccount.BankAccount

interface ITransfer {
    val id: Int
    val fromAccount: BankAccount
    val toAccount: BankAccount
    val amount: Double
    val status: StatusTransfer
}