package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.user.BaseUser
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetCreditBankAccountByBaseUserUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke(baseUser: BaseUser) : Flow<List<ICreditBankAccount>> {
        return bankAccountRepository.getCreditBankAccountByBaseUserId(baseUserId = baseUser.id)
    }
}