package com.example.domain.models.transfer

import com.example.domain.models.bank.bankAccount.BaseBankAccount

data class Transfer(
    val id: Int,
    override val fromBaseBankAccount: BaseBankAccount,
    override val toBaseBankAccount: BaseBankAccount,
    override val amount: Double,
    override val dateTransfer: String,
    override val timeTransfer: String,
    override val status: StatusTransfer
) : ITransfer
