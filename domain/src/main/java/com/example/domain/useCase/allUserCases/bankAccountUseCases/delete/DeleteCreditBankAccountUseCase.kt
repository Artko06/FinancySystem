package com.example.domain.useCase.allUserCases.bankAccountUseCases.delete

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.repository.BankAccountRepository

class DeleteCreditBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(creditBankAccount: ICreditBankAccount){
        bankAccountRepository.deleteCreditBankAccount(bankAccount = creditBankAccount)
    }
}