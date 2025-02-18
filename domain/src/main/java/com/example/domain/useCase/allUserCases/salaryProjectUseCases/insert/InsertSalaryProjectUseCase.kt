package com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.repository.SalaryProjectRepository

class InsertSalaryProjectUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    suspend operator fun invoke(salaryProjectCompany: ISalaryProjectCompany){
        salaryProjectRepository.insertSalaryProject(
            salaryProjectCompany = salaryProjectCompany
        )
    }
}