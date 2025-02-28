package com.example.domain.useCase.allUserCases.bankAccountUseCases.change

import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.repository.BankAccountRepository

class ChangeStatusCreditBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend operator fun invoke(creditBankAccountId: Int, statusCreditBid: StatusCreditBid){
        bankAccountRepository.changeStatusCreditBankAccount(
            creditBankAccount = creditBankAccountId,
            statusCreditBid = statusCreditBid
        )
    }
}