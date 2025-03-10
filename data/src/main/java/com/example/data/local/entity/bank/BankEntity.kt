package com.example.data.local.entity.bank

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.Bank

@Entity(tableName = "banks")
data class BankEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val bic: String, // БИК банка
    val interestRate: Double
)

fun BankEntity.toDomain() = Bank(
    id = id,
    name = name,
    bic = bic,
    interestRate = interestRate
)

fun Bank.toEntity() = BankEntity(
    id = id,
    name = name,
    bic = bic,
    interestRate = interestRate
)