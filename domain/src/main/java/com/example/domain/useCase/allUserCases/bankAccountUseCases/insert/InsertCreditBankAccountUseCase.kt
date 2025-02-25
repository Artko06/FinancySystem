package com.example.domain.useCase.allUserCases.bankAccountUseCases.insert

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.models.user.BaseUser
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.firstOrNull

class InsertCreditBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(
        bank: Bank,
        baseUser: BaseUser,
        balance: Double,
        interestRate: Double,
        creditLastDate: String,
        creditTotalSum: Double
    )
    {
        var baseBankAccount = BaseBankAccount(
            id = 0,
            bank = bank,
            baseUser = baseUser,
            balance = balance,
            statusBankAccount = StatusBankAccount.NORMAL
        )

        bankAccountRepository.insertBaseBankAccount(baseBankAccount)
        baseBankAccount = bankAccountRepository.getLatestBaseBankAccountByBaseUserId(baseUser.id).firstOrNull()!!

        bankAccountRepository.insertCreditBankAccount(
            bankAccount = CreditBankAccount(
                id = 0,
                baseBankAccount = baseBankAccount,
                interestRate = interestRate,
                creditLastDate = creditLastDate,
                creditTotalSum = creditTotalSum,
                statusCreditBid = StatusCreditBid.WAITING
            )
        )
    }
}