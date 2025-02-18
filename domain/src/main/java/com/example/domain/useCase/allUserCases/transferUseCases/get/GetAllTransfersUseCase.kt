package com.example.domain.useCase.allUserCases.transferUseCases.get

import com.example.domain.models.transfer.ITransfer
import com.example.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransfersUseCase(
    private val transferRepository: TransferRepository
)
{
    operator fun invoke() : Flow<List<ITransfer>> {
        return transferRepository.getAllTransfers()
    }
}