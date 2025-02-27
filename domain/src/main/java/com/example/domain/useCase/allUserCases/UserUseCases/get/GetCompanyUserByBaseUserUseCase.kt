package com.example.domain.useCase.allUserCases.UserUseCases.get

import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCompanyUserByBaseUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(baseUserId: Int): Flow<CompanyUser?>{
        return userRepository.getCompanyUserByBaseUserId(baseUserId)
    }
}