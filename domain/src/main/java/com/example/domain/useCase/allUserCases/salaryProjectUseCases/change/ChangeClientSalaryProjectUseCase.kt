package com.example.domain.useCase.allUserCases.salaryProjectUseCases.change

import com.example.domain.repository.SalaryProjectRepository

class ChangeClientSalaryProjectUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    suspend operator fun invoke(salaryProjectId: Int, clientUserId: Int?){
        salaryProjectRepository.changeClientSalaryProject(
            salaryProjectId = salaryProjectId,
            clientBaseUserId = clientUserId
        )
    }
}