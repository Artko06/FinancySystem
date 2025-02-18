package com.example.domain.useCase.allUserCases.bankAccountUseCases.insert

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.repository.BankAccountRepository

class InsertCreditBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(creditBankAccount: ICreditBankAccount){
        bankAccountRepository.insertCreditBankAccount(bankAccount = creditBankAccount)
    }
}