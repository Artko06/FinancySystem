package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.models.user.BaseUser
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetStandardBankAccountsByBaseUserUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke(baseUser: BaseUser) : Flow<List<IStandardBankAccount>> {
        return bankAccountRepository.getStandardBankAccountByBaseUserId(baseUserId = baseUser.id)
    }
}