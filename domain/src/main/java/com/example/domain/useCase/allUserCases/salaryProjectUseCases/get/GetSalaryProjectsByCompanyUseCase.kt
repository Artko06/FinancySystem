package com.example.domain.useCase.allUserCases.salaryProjectUseCases.get

import com.example.domain.models.company.Company
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.repository.SalaryProjectRepository
import kotlinx.coroutines.flow.Flow

class GetSalaryProjectsByCompanyUseCase(
    private val salaryProjectRepository: SalaryProjectRepository
)
{
    operator fun invoke(company: Company) : Flow<List<ISalaryProjectCompany>> {
        return salaryProjectRepository.getSalaryProjectsByCompanyId(
            companyId = company.id
        )
    }
}