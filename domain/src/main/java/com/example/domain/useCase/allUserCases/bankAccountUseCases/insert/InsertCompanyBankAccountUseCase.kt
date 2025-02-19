package com.example.domain.useCase.allUserCases.bankAccountUseCases.insert

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.repository.BankAccountRepository

class InsertCompanyBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(companyBankAccount: ICompanyBankAccount){
        bankAccountRepository.insertCompanyBankAccount(bankAccount = companyBankAccount)
    }
}