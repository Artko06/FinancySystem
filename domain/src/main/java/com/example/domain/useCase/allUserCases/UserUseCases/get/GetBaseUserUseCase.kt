package com.example.domain.useCase.allUserCases.UserUseCases.get

import com.example.domain.models.user.BaseUser
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetBaseUserUseCase(
    private val userRepository: UserRepository
)
{
    operator fun invoke(email: String): Flow<BaseUser?> {
        return userRepository.getBaseUserByEmail(email = email)
    }
}