package com.example.domain.useCase.allUserCases.bankAccountUseCases.insert

import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.repository.BankAccountRepository

class InsertStandardBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(standardBankAccount: IStandardBankAccount){
        bankAccountRepository.insertStandardBankAccount(bankAccount = standardBankAccount)
    }
}