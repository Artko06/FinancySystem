package com.example.domain.useCase.allUserCases.bankAccountUseCases.delete

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.repository.BankAccountRepository

class DeleteCompanyBankAccountUseCase(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun invoke(companyBankAccount: ICompanyBankAccount) {
        bankAccountRepository.deleteCompanyBankAccount(bankAccount = companyBankAccount)
    }
}
