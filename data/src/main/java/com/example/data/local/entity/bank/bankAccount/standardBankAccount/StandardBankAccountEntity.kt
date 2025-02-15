package com.example.data.local.entity.bank.bankAccount.standardBankAccount

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount

@Entity(tableName = "standard_bank_accounts")
data class StandardBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankId: Int,
    val userId: Int,
    val balance: Double,
    val statusBankAccount: String
)

//fun StandardBankAccountEntity.toDomain() = StandardBankAccount(
//    id = id,
//    bank = getBankById(bankId = bankId),
//    user = getUserByID(userId = userId),
//    balance = balance,
//    statusBankAccount = enumValueOf<StatusBankAccount>(statusBankAccount)
//)
