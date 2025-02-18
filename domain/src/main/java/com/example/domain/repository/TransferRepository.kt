package com.example.domain.repository

import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    fun getAllTransfers(): Flow<List<ITransfer>>
    suspend fun changeStatusTransfer(transfer: Transfer, statusTransfer: StatusTransfer)
    suspend fun insertTransfer(transfer: ITransfer)
    suspend fun deleteTransfer(transfer: ITransfer)
}