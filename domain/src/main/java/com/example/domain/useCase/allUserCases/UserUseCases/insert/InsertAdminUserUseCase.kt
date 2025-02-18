package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.adminUser.AdminUser
import com.example.domain.repository.UserRepository

class InsertAdminUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(adminUser: AdminUser){
        userRepository.insertAdminUser(adminUser = adminUser)
    }
}