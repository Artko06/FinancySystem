package com.example.domain.useCase.allUserCases.bankUseCases.delete

import com.example.domain.repository.BankAccountRepository

class DeleteAllBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend operator fun invoke() {
        bankAccountRepository.deleteAllBaseBankAccounts()
    }
}