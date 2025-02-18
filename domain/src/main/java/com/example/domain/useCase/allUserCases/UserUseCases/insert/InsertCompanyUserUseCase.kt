package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.repository.UserRepository

class InsertCompanyUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(companyUser: CompanyUser){
        userRepository.insertCompanyUser(companyUser = companyUser)
    }
}