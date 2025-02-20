package com.example.domain.useCase.allUserCases.UserUseCases.other

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class ValidatePasswordUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(email: String, password: String): Boolean {
        val baseUser = userRepository.getBaseUserByEmail(email).firstOrNull()

        if(baseUser == null) return false

        val passwordFromRepository = userRepository.getCertificateUserByBaseUserId(baseUser.id)
            .firstOrNull()?.hashedPassword

        return passwordFromRepository == password
    }
}