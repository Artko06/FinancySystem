package com.example.domain.useCase.allUserCases.salaryProjectUseCases.get

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.user.BaseUser
import com.example.domain.repository.SalaryProjectRepository
import kotlinx.coroutines.flow.Flow

class GetSalaryProjectsByClientBaseUserUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    operator fun invoke(baseUser: BaseUser) : Flow<List<ISalaryProjectCompany>> {
        return salaryProjectRepository.getSalaryProjectsByClientBaseUserId(
            clientBaseUserId = baseUser.id
        )
    }
}