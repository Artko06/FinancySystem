package com.example.domain.useCase.allUserCases.salaryProjectUseCases.delete

import com.example.domain.repository.SalaryProjectRepository

class DeleteAllSalaryProjectUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
) {
    suspend operator fun invoke(){
        salaryProjectRepository.deleteAllSalaryProjects()
    }
}