package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.CertificateUser
import com.example.domain.repository.UserRepository

class InsertCertificateUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(certificateUser: CertificateUser) {
        userRepository.insertCertificateUser(certificateUser = certificateUser)
    }
}