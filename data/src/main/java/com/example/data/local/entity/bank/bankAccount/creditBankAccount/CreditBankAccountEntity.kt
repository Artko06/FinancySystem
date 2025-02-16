package com.example.data.local.entity.bank.bankAccount.creditBankAccount

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid

@Entity(
    tableName = "credit_bank_accounts",
    foreignKeys = [
        ForeignKey(
            entity = BaseBankAccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseBankAccountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["baseBankAccountId"])]
)
data class CreditBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseBankAccountId: Int,
    val interestRate: Double,
    val creditLastDate: String,
    val creditTotalSum: Double,
    val statusCreditBid: String
)

fun CreditBankAccountEntity.toDomain(baseBankAccount: BaseBankAccount) = CreditBankAccount(
    id = id,
    baseBankAccount = baseBankAccount,
    interestRate = interestRate,
    creditLastDate = creditLastDate,
    creditTotalSum = creditTotalSum,
    statusCreditBid = enumValueOf<StatusCreditBid>(statusCreditBid)
)

fun CreditBankAccount.toEntity(baseBankAccountId: Int) = CreditBankAccountEntity(
    id = id,
    baseBankAccountId = baseBankAccountId,
    interestRate = interestRate,
    creditLastDate = creditLastDate,
    creditTotalSum = creditTotalSum,
    statusCreditBid = statusCreditBid.toString()
)
