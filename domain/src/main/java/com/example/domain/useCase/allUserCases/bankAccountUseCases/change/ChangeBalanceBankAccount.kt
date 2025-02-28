package com.example.domain.useCase.allUserCases.bankAccountUseCases.change

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.repository.BankAccountRepository

class ChangeBalanceBankAccount(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend operator fun invoke(
        account: BaseBankAccount,
        newBalance: Double
    ){
        bankAccountRepository.changeBalanceBaseBankAccount(
            bankAccount = account,
            balance = newBalance
        )
    }
}