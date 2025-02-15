package com.example.data.local.entity.transfer

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer

@Entity(tableName = "transfers")
data class TransferEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fromAccountId: Int,
    val toAccountId: Int,
    val amount: Double,
    val status: String
)

//fun TransferEntity.toDomain() = Transfer(
//    id = id,
//    fromAccount = getBankAccountById(fromAccountId),
//    toAccount = getBankAccountById(toAccountId),
//    amount = amount,
//    status = enumValueOf<StatusTransfer>(status)
//)