package com.example.domain.useCase.allUserCases.bankAccountUseCases.delete

import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.repository.BankAccountRepository

class DeleteStandardBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(standardBankAccount: IStandardBankAccount){
        bankAccountRepository.deleteStandardBankAccount(bankAccount = standardBankAccount)
    }
}