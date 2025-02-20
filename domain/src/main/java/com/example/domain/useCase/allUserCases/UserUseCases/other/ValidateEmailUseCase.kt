package com.example.domain.useCase.allUserCases.UserUseCases.other

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class ValidateEmailUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(email: String): Boolean {
        val baseUser = userRepository.getBaseUserByEmail(email).firstOrNull()

        return baseUser != null
    }
}