package com.example.data.local.entity.bank.bankAccount

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.user.BaseUser

@Entity(tableName = "base_bank_accounts")
data class BaseBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankId: Int,
    val baseUserId: Int,
    val balance: Double,
    val statusBankAccount: String
)

fun BaseBankAccountEntity.toDomain(baseUser: BaseUser, bank: Bank) = BaseBankAccount(
    id = id,
    bank = bank,
    baseUser = baseUser,
    balance = balance,
    statusBankAccount = enumValueOf<StatusBankAccount>(statusBankAccount)
)

fun BaseBankAccount.toEntity(baseUserId: Int, bankId: Int) = BaseBankAccountEntity(
    id = id,
    bankId = bankId,
    baseUserId = baseUserId,
    balance = balance,
    statusBankAccount = statusBankAccount.toString()
)