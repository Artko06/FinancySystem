package com.example.domain.useCase.allUserCases.bankAccountUseCases.get

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.company.Company
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.Flow

class GetCompanyBankAccountsByCompanyUseCase(
    private val bankAccountRepository: BankAccountRepository
)
{
    operator fun invoke(company: Company): Flow<List<ICompanyBankAccount>> {
        return bankAccountRepository.getCompanyBankAccountsByCompanyId(companyId = company.id)
    }
}