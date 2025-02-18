package com.example.domain.useCase.allUserCases.salaryProjectUseCases.change

import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.domain.repository.SalaryProjectRepository

class ChangeStatusSalaryProjectUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    suspend operator fun invoke(salaryProjectCompany: SalaryProjectCompany, statusJobBid: StatusJobBid){
        salaryProjectRepository.changeStatusSalaryProject(
            salaryProjectCompany = salaryProjectCompany,
            statusJobBid = statusJobBid
        )
    }
}