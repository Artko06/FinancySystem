package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.clientUser.ClientUser
import com.example.domain.repository.UserRepository

class InsertClientUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(clientUser: ClientUser){
        userRepository.insertClientUser(clientUser = clientUser)
    }
}