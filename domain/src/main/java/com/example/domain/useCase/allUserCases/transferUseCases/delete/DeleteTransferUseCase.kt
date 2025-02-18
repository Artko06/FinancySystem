package com.example.domain.useCase.allUserCases.transferUseCases.delete

import com.example.domain.models.transfer.ITransfer
import com.example.domain.repository.TransferRepository

class DeleteTransferUseCase(
    private val transferRepository: TransferRepository
)
{
    suspend operator fun invoke(transfer: ITransfer){
        transferRepository.deleteTransfer(transfer = transfer)
    }
}