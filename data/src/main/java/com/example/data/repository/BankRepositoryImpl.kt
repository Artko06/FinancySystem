package com.example.data.repository

import com.example.data.local.dao.BankDao
import com.example.data.local.entity.bank.toDomain
import com.example.data.local.entity.bank.toEntity
import com.example.domain.models.bank.Bank
import com.example.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BankRepositoryImpl(
    private val bankDao: BankDao
) : BankRepository {
    override fun getAllBanks(): Flow<List<Bank>> {
        return bankDao.getAllBanks().map { banksEntity ->
            banksEntity.map { bankEntity ->
                bankEntity.toDomain()
            }
        }
    }

    override fun getBankById(bankId: Int): Flow<Bank?> {
        return bankDao.getBankById(bankId).map { bankEntity ->
            bankEntity?.toDomain() }
    }

    override suspend fun insertBank(bank: Bank) {
        bankDao.insertBank(bank = bank.toEntity())
    }

    override suspend fun deleteBank(bank: Bank) {
        bankDao.deleteBank(bank = bank.toEntity())
    }
}