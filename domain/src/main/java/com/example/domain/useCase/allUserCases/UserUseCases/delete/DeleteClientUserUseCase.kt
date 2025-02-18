package com.example.domain.useCase.allUserCases.UserUseCases.delete

import com.example.domain.models.user.clientUser.ClientUser
import com.example.domain.repository.UserRepository

class DeleteClientUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(clientUser: ClientUser){
        userRepository.deleteClientUser(clientUser = clientUser)
    }
}