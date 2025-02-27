package com.example.domain.useCase.allUserCases.transferUseCases.other.validateTransfer

import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.flow.firstOrNull

class ValidateTransferUseCase(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend operator fun invoke(cardFromId: String, cardToId: String, sum: String): ValidateTransfer {

        if(cardToId.toUIntOrNull() == null) return ValidateTransfer.INCORRECT_CARD_GETTER
        if(cardFromId.toUIntOrNull() == null) return ValidateTransfer.INCORRECT_CARD_SENDER
        if(sum.toDoubleOrNull() == null) return ValidateTransfer.INCORRECT_SUM

        val fromBankAccount = bankAccountRepository.getBaseBankAccountById(cardFromId.toInt()).firstOrNull()
        val toBankAccount = bankAccountRepository.getBaseBankAccountById(cardToId.toInt()).firstOrNull()
        val fromCreditAccount = bankAccountRepository.getCreditBankAccountByBaseBankAccountId(cardFromId.toInt()).firstOrNull()

        return when{
            fromBankAccount == null -> ValidateTransfer.INCORRECT_CARD_GETTER

            toBankAccount == null -> ValidateTransfer.INCORRECT_CARD_SENDER

            sum.toDouble() <= 0.0 -> ValidateTransfer.INCORRECT_SUM

            fromBankAccount.balance < sum.toDouble() -> ValidateTransfer.NOT_ENOUGH_SUM

            toBankAccount.statusBankAccount == StatusBankAccount.BLOCKED -> ValidateTransfer.BLOCK_ACCOUNT_GETTER

            fromBankAccount.statusBankAccount != StatusBankAccount.NORMAL
                -> ValidateTransfer.FROZEN_BLOCKED_ACCOUNT_SENDER

//            fromCreditAccount != null && fromCreditAccount.statusCreditBid != StatusCreditBid.ACCEPTED
//                -> ValidateTransfer.NOT_ACCEPTED_CREDIT_ACCOUNT

            else -> ValidateTransfer.OK
        }
    }


}