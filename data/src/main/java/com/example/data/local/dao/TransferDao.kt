package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.transfer.TransferEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransferDao {
    @Query("SELECT * FROM transfers ORDER BY dateTransfer DESC, timeTransfer DESC")
    fun getAllTransfers(): Flow<List<TransferEntity>>

    @Upsert
    suspend fun insertTransfer(transfer: TransferEntity)

    @Delete
    suspend fun deleteTransfer(transfer: TransferEntity)
}