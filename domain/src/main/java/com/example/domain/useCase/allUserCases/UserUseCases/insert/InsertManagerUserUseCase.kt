package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.managerUser.ManagerUser
import com.example.domain.repository.UserRepository

class InsertManagerUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(managerUser: ManagerUser){
        userRepository.insertManagerUser(managerUser = managerUser)
    }
}