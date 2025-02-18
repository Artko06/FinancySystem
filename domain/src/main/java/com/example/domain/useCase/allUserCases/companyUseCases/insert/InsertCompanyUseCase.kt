package com.example.domain.useCase.allUserCases.companyUseCases.insert

import com.example.domain.models.company.Company
import com.example.domain.repository.CompanyRepository

class InsertCompanyUseCase(
    private val companyRepository: CompanyRepository
)
{
    suspend operator fun invoke(company: Company) {
        companyRepository.deleteCompany(company = company)
    }
}