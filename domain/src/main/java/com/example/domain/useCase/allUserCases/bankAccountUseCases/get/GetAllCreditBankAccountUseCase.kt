package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetAllCreditBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke() : Flow<List<ICreditBankAccount>> {
        return bankAccountRepository.getAllCreditBankAccounts()
    }
}