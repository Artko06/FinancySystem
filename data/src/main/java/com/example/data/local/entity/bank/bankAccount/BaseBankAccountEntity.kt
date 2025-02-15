package com.example.data.local.entity.bank.bankAccount

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount

@Entity(tableName = "base_bank_accounts")
data class BaseBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankId: Int,
    val baseUserId: Int,
    val balance: Double,
    val statusBankAccount: String
)

//fun BaseBankAccountEntity.toDomain() = BaseBankAccount(
//    id = id,
//    bank = getBankById(bankId = bankId),
//    baseUser = getBaseUserById(baseUserId = baseUserId),
//    balance = balance,
//    statusBankAccount = enumValueOf<StatusBankAccount>(statusBankAccount)
//)