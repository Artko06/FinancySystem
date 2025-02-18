package com.example.domain.useCase.allUserCases.UserUseCases.delete

import com.example.domain.models.user.operatorUser.OperatorUser
import com.example.domain.repository.UserRepository

class DeleteOperatorUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(operatorUser: OperatorUser){
        userRepository.deleteOperatorUser(operatorUser = operatorUser)
    }
}