package com.example.domain.useCase.allUserCases.transferUseCases.other

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer
import com.example.domain.repository.BankAccountRepository
import com.example.domain.repository.TransferRepository

class CreateTransferUseCase(
    private val bankAccountRepository: BankAccountRepository,
    private val transferRepository: TransferRepository
) {
    suspend operator fun invoke(
        fromBaseBankAccount: BaseBankAccount,
        toBaseBankAccount: BaseBankAccount,
        amount: Double,
        dateTransfer: String,
        timeTransfer: String,
    ){
        transferRepository.insertTransfer(
            Transfer(
                id = 0,
                fromBaseBankAccount = fromBaseBankAccount,
                toBaseBankAccount = toBaseBankAccount,
                amount = amount,
                dateTransfer = dateTransfer,
                timeTransfer = timeTransfer,
                status = StatusTransfer.SUCCESS
            )
        )

        bankAccountRepository.changeBalanceBaseBankAccount(
            bankAccount = fromBaseBankAccount,
            balance = fromBaseBankAccount.balance - amount
        )

        bankAccountRepository.changeBalanceBaseBankAccount(
            bankAccount = toBaseBankAccount,
            balance = toBaseBankAccount.balance + amount
        )
    }
}