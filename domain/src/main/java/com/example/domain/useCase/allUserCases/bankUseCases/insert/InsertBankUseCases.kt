package com.example.domain.useCase.allUserCases.bankUseCases.insert

import com.example.domain.models.bank.Bank
import com.example.domain.repository.BankRepository

class InsertBankUseCases(
    private val bankRepository: BankRepository
)
{
    suspend operator fun invoke(bank: Bank){
        bankRepository.insertBank(bank = bank)
    }
}