package com.example.domain.models.transfer

import com.example.domain.models.bank.bankAccount.BankAccount

data class Transfer(
    override val id: Int,
    override val fromAccount: BankAccount,
    override val toAccount: BankAccount,
    override val amount: Double,
    override val status: StatusTransfer
) : ITransfer
