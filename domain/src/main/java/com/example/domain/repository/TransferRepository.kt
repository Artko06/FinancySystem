package com.example.domain.repository

import com.example.domain.models.transfer.ITransfer
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    fun getAllTransfers(): Flow<List<ITransfer>>
    suspend fun insertTransfer(transfer: ITransfer)
    suspend fun deleteTransfer(transfer: ITransfer)
}