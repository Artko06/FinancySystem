package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.operatorUser.OperatorUser
import com.example.domain.repository.UserRepository

class InsertOperatorUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(operatorUser: OperatorUser){
        userRepository.insertOperatorUser(operatorUser = operatorUser)
    }
}