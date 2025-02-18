package com.example.domain.useCase.allUserCases.transferUseCases.insert

import com.example.domain.models.transfer.ITransfer
import com.example.domain.repository.TransferRepository

class InsertTransferUseCase(
    private val transferRepository: TransferRepository
)
{
    suspend operator fun invoke(transfer: ITransfer){
        transferRepository.insertTransfer(transfer = transfer)
    }
}