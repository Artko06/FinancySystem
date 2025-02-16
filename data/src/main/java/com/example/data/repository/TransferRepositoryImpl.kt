package com.example.data.repository

import com.example.data.local.dao.TransferDao
import com.example.domain.models.transfer.ITransfer
import com.example.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow

class TransferRepositoryImpl(
    private val transferDao: TransferDao
) : TransferRepository
{
    override fun getAllTransfers(): Flow<List<ITransfer>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTransfer(transfer: ITransfer) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransfer(transfer: ITransfer) {
        TODO("Not yet implemented")
    }
}