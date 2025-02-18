package com.example.domain.useCase.allUserCases.companyUseCases.delete

import com.example.domain.models.company.Company
import com.example.domain.repository.CompanyRepository

class DeleteCompanyUseCase(
    private val companyRepository: CompanyRepository
)
{
    suspend operator fun invoke(company: Company) {
        companyRepository.insertCompany(company = company)
    }
}