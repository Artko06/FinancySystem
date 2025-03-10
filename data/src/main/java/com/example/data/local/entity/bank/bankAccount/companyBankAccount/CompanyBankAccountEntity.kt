package com.example.data.local.entity.bank.bankAccount.companyBankAccount

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.companyBankAccount.CompanyBankAccount
import com.example.domain.models.company.Company

@Entity(
    tableName = "company_bank_accounts",
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
data class CompanyBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseBankAccountId: Int,
    val companyId: Int
)

fun CompanyBankAccountEntity.toDomain(baseBankAccount: BaseBankAccount, company: Company) = CompanyBankAccount(
    id = id,
    baseBankAccount = baseBankAccount,
    company = company
)

fun CompanyBankAccount.toEntity(baseBankAccountId: Int, companyId: Int) = CompanyBankAccountEntity(
    id = id,
    baseBankAccountId = baseBankAccountId,
    companyId = companyId
)
