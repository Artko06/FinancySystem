package com.example.domain.models.bank.bankAccount.creditBankAccount

interface ICreditBankAccount {
    val interestRate: Double
    val countMonthsCredit: Int
    val creditLastDate: String
    val creditTotalSum: Double
    val statusCreditBid: StatusCreditBid
}