package com.example.domain.useCase.allUserCases.UserUseCases.delete

import com.example.domain.models.user.BaseUser
import com.example.domain.repository.UserRepository

class DeleteBaseUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(baseUser: BaseUser){
        userRepository.deleteBaseUser(baseUser = baseUser)
    }
}