package com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert

import com.example.domain.models.company.Company
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.domain.repository.SalaryProjectRepository


class InsertSalaryProjectUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    suspend operator fun invoke(info: String, sum: Double, company: Company){

        val salaryProject = SalaryProjectCompany(
            id = 0,
            clientBaseUser = null,
            company = company,
            status = StatusJobBid.WAITING,
            sum = sum,
            info = info
        )


        salaryProjectRepository.insertSalaryProject(salaryProject)
    }
}