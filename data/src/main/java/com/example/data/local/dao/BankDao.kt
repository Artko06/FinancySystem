package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.bank.BankEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {
    @Query("SELECT * FROM banks")
    fun getAllBanks(): Flow<List<BankEntity>>

    @Query("SELECT * FROM banks WHERE id = :bankId")
    fun getBankById(bankId: Int): Flow<BankEntity?>

    @Upsert
    suspend fun insertBank(bank: BankEntity)

    @Delete
    suspend fun deleteBank(bank: BankEntity)
}