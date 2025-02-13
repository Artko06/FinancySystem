package com.example.domain.models.bank.bankAccount.creditBankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.user.ClientBank

data class CreditBankAccount(
    val interestRate: Double,
    val creditLastDate: String,
    val creditTotalSum: Int,
    val statusCreditBid: StatusCreditBid,

    override val id: String,
    override val bank: Bank,
    override val user: ClientBank,
    override val balance: Double,
    override val statusBankAccount: StatusBankAccount
) : IBankAccount