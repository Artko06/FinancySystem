package com.example.domain.useCase.allUserCases.UserUseCases.delete

import com.example.domain.models.user.adminUser.AdminUser
import com.example.domain.repository.UserRepository

class DeleteAdminUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(adminUser: AdminUser){
        userRepository.deleteAdminUser(adminUser = adminUser)
    }
}