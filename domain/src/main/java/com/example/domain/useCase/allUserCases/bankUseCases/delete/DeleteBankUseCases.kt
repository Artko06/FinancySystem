package com.example.domain.useCase.allUserCases.bankUseCases.delete

import com.example.domain.models.bank.Bank
import com.example.domain.repository.BankRepository

class DeleteBankUseCases(
    private val bankRepository: BankRepository
)
{
    suspend operator fun invoke(bank: Bank){
        bankRepository.deleteBank(bank = bank)
    }
}