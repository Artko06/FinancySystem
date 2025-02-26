package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetBaseBankAccountById(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke(id: Int) : Flow<BaseBankAccount?> {
        return bankAccountRepository.getBaseBankAccountById(id)
    }
}