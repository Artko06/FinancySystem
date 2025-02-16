package com.example.data.local.entity.bank.bankAccount.standardBankAccount

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount


@Entity(
    tableName = "standard_bank_accounts",
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
data class StandardBankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseBankAccountId: Int
)

fun StandardBankAccountEntity.toDomain(baseBankAccount: BaseBankAccount) = StandardBankAccount(
    id = id,
    baseBankAccount = baseBankAccount
)

fun StandardBankAccount.toEntity(baseBankAccountId: Int) = StandardBankAccountEntity(
    id = id,
    baseBankAccountId = baseBankAccountId
)
