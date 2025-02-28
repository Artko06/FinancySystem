package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetAllStandardBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke() : Flow<List<IStandardBankAccount>> {
        return bankAccountRepository.getAllStandardBankAccounts()
    }
}