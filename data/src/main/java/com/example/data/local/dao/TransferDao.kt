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

    @Query("SELECT * FROM transfers WHERE id = :transferId")
    fun getTransferById(transferId: Int): Flow<TransferEntity?>

    @Query("UPDATE transfers SET status = :newStatus WHERE id = :transferId")
    suspend fun changeTransferStatus(transferId: Int, newStatus: String)

    @Upsert
    suspend fun insertTransfer(transfer: TransferEntity)

    @Delete
    suspend fun deleteTransfer(transfer: TransferEntity)
}