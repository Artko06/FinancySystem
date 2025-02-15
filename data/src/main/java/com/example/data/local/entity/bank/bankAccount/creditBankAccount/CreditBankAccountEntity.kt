package com.example.data.local.entity.bank.bankAccount.creditBankAccount

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid

@Entity(tableName = "credit_bank_accounts")
data class CreditBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankId: Int,
    val userId: Int,
    val balance: Double,
    val statusBankAccount: String,

    val interestRate: Double,
    val creditLastDate: String,
    val creditTotalSum: Double,
    val statusCreditBid: String
)

//fun CreditBankAccountEntity.toDomain() = CreditBankAccount(
//    id = id,
//    bank = getBankById(bankId = bankId),
//    user = getUserByID(userId = userId),
//    balance = balance,
//    statusBankAccount = enumValueOf<StatusBankAccount>(statusBankAccount),
//    interestRate = interestRate,
//    creditLastDate = creditLastDate,
//    creditTotalSum = creditTotalSum,
//    statusCreditBid = enumValueOf<StatusCreditBid>(statusCreditBid)
//)
