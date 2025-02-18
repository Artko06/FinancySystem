package com.example.domain.useCase.allUserCases.UserUseCases.delete

import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.repository.UserRepository

class DeleteCompanyUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(companyUser: CompanyUser){
        userRepository.deleteCompanyUser(companyUser = companyUser)
    }
}