package com.example.domain.useCase.allUserCases.bankAccountUseCases.change

import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.repository.BankAccountRepository

class ChangeStatusCreditBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend operator fun invoke(creditBankAccount: CreditBankAccount, statusCreditBid: StatusCreditBid){
        bankAccountRepository.changeStatusCreditBankAccount(
            bankAccount = creditBankAccount,
            statusCreditBid = statusCreditBid
        )
    }
}