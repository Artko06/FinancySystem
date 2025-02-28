package com.example.domain.useCase.allUserCases.transferUseCases.get

import com.example.domain.models.transfer.ITransfer
import com.example.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow

class GetTransferById(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(transferId: Int) : Flow<ITransfer?> {
        return transferRepository.getTransferById(transferId)
    }
}