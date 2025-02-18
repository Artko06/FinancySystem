package com.example.domain.useCase.allUserCases.companyUseCases.get

import com.example.domain.models.company.Company
import com.example.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow

class GetAllCompanyUseCase(
    private val companyRepository: CompanyRepository
)
{
    operator fun invoke() : Flow<List<Company>> {
        return companyRepository.getAllCompanies()
    }
}