package com.example.domain.useCase.allUserCases.bankUseCases.get

import com.example.domain.models.bank.Bank
import com.example.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow

class GetAllBanksUseCases(
    private val bankRepository: BankRepository
)
{
    operator fun invoke() : Flow<List<Bank>> {
        return bankRepository.getAllBanks()
    }
}