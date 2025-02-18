package com.example.domain.useCase.allUserCases.salaryProjectUseCases.delete

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.repository.SalaryProjectRepository

class DeleteSalaryProjectCompany(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    suspend operator fun invoke(salaryProjectCompany: ISalaryProjectCompany){
        salaryProjectRepository.deleteSalaryProject(
            salaryProjectCompany = salaryProjectCompany
        )
    }
}