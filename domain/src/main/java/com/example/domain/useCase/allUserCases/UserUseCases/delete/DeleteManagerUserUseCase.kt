package com.example.domain.useCase.allUserCases.UserUseCases.delete

import com.example.domain.models.user.managerUser.ManagerUser
import com.example.domain.repository.UserRepository

class DeleteManagerUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(managerUser: ManagerUser){
        userRepository.deleteManagerUser(managerUser = managerUser)
    }
}