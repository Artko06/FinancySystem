package com.example.domain.useCase.allUserCases.bankAccountUseCases.insert

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount
import com.example.domain.models.user.BaseUser
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.firstOrNull

class InsertStandardBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    suspend fun invoke(
        bank: Bank,
        baseUser: BaseUser,
        balance: Double
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


        bankAccountRepository.insertStandardBankAccount(bankAccount = StandardBankAccount(
                id = 0,
                baseBankAccount = baseBankAccount
            )
        )
    }
}