package com.example.data.local.entity.bank.bankAccount.companyBankAccount

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.companyBankAccount.CompanyBankAccount
import com.example.domain.models.company.Company

@Entity(tableName = "company_bank_accounts")
data class CompanyBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bankId: Int,
    val userId: Int,
    val balance: Double,
    val statusBankAccount: String,

    val companyId: Int
)

//fun CompanyBankAccountEntity.toDomain = CompanyBankAccount(
//    id = id,
//    bank = getBankById(bankId),
//    user = getUserById(userId),
//    balance = balance,
//    statusBankAccount = enumValueOf<StatusBankAccount>(statusBankAccount)
//)