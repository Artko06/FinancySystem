package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetAllCompanyBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke() : Flow<List<ICompanyBankAccount>> {
        return bankAccountRepository.getAllCompanyBankAccounts()
    }
}