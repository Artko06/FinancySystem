package com.example.domain.useCase.allUserCases.transferUseCases.change

import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer
import com.example.domain.repository.TransferRepository

class ChangeStatusTransferUseCase(
    private val transferRepository: TransferRepository,
)
{
    suspend operator fun invoke(transfer: Transfer, statusTransfer: StatusTransfer){
        transferRepository.changeStatusTransfer(
            transfer = transfer,
            statusTransfer = statusTransfer
        )
    }
}