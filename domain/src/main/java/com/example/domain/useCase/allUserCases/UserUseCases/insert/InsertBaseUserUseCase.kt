package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.BaseUser
import com.example.domain.repository.UserRepository

class InsertBaseUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(baseUser: BaseUser){
        userRepository.insertBaseUser(baseUser = baseUser)
    }
}