package com.example.domain.useCase.allUserCases.bankAccountUseCases.change

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.repository.BankAccountRepository

class ChangeStatusBaseBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend operator fun invoke(baseBankAccount: BaseBankAccount, statusBankAccount: StatusBankAccount){
        bankAccountRepository.changeStatusBaseBankAccount(
            bankAccount = baseBankAccount,
            statusBankAccount = statusBankAccount
        )
    }
}