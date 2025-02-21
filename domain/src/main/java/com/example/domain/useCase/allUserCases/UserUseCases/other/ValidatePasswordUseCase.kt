package com.example.domain.useCase.allUserCases.UserUseCases.other

import com.example.domain.repository.UserRepository
import com.example.domain.util.PasswordHasher
import kotlinx.coroutines.flow.firstOrNull

class ValidatePasswordUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(email: String, password: String): Boolean {
        val baseUser = userRepository.getBaseUserByEmail(email).firstOrNull()

        if(baseUser == null) return false

        val hashPasswordFromRepository = userRepository.getCertificateUserByBaseUserId(baseUser.id)
            .firstOrNull()?.hashedPassword

        if(hashPasswordFromRepository == null) return false

        return PasswordHasher.verifyPassword(password, hashPasswordFromRepository)
    }
}