package com.example.domain.repository

import com.example.domain.models.bank.Bank
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    fun getAllBanks(): Flow<List<Bank>>
    fun getBankById(bankId: Int): Flow<Bank?>
    suspend fun insertBank(bank: Bank)
    suspend fun insertListOfBank(banks: List<Bank>)
    suspend fun deleteBank(bank: Bank)
}