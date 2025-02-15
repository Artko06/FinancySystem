package com.example.domain.models.bank.bankAccount.creditBankAccount

import com.example.domain.models.bank.bankAccount.BaseBankAccount

data class CreditBankAccount(
    val baseBankAccount: BaseBankAccount,
    override val interestRate: Double,
    override val creditLastDate: String,
    override val creditTotalSum: Double,
    override val statusCreditBid: StatusCreditBid
) : ICreditBankAccount