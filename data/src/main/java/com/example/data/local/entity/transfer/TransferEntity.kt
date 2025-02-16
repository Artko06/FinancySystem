package com.example.data.local.entity.transfer

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer

@Entity(tableName = "transfers")
data class TransferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fromBaseBankAccountId: Int,
    val toBaseBankAccountId: Int,
    val amount: Double,
    val dateTransfer: String,
    val timeTransfer: String,
    val status: String
)

fun TransferEntity.toDomain(
    fromBaseBankAccount: BaseBankAccount,
    toBaseBankAccount: BaseBankAccount
) = Transfer(
    id = id,
    fromBaseBankAccount = fromBaseBankAccount,
    toBaseBankAccount = toBaseBankAccount,
    amount = amount,
    dateTransfer = dateTransfer,
    timeTransfer = timeTransfer,
    status = enumValueOf<StatusTransfer>(status)
)

fun Transfer.toEntity(
    fromBaseBankAccountId: Int,
    toBaseBankAccountId: Int
) = TransferEntity(
    id = id,
    fromBaseBankAccountId = fromBaseBankAccountId,
    toBaseBankAccountId = toBaseBankAccountId,
    amount = amount,
    dateTransfer = dateTransfer,
    timeTransfer = timeTransfer,
    status = status.toString()
)