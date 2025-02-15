package com.example.domain.models.bank.bankAccount.creditBankAccount

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.BankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.user.ClientBank

data class CreditBankAccount(
    val interestRate: Double,
    val creditLastDate: String,
    val creditTotalSum: Double,
    val statusCreditBid: StatusCreditBid,

    override val id: Int,
    override val bank: Bank,
    override val user: ClientBank,
    override val balance: Double,
    override val statusBankAccount: StatusBankAccount
) : BankAccount()