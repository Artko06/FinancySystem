package com.example.domain.useCase.allUserCases.bankAccountUseCases.change

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.repository.BankAccountRepository

class ChangeBalanceBaseBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend operator fun invoke(baseBankAccount: BaseBankAccount, balance: Double){
        bankAccountRepository.changeBalanceBaseBankAccount(
            bankAccount = baseBankAccount,
            balance = balance
        )
    }
}