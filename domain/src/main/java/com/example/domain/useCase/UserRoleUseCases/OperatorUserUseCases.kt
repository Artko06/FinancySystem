package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.roles.OperatorUserRole
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase

data class OperatorUserUseCases(
    override val getAllTransfersUseCase: GetAllTransfersUseCase
) : OperatorUserRole