package com.example.domain.models.transfer

import com.example.domain.models.bank.bankAccount.IBankAccount

data class Transfer(
    override val id: String,
    override val fromAccount: IBankAccount,
    override val toAccount: IBankAccount,
    override val amount: Double,
    override val status: StatusTransfer
) : ITransfer
