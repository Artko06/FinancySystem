package com.example.domain.useCase.allUserCases.salaryProjectUseCases.get

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.domain.repository.SalaryProjectRepository
import kotlinx.coroutines.flow.Flow

class GetSalaryProjectsByStatus(
    private val salaryProjectRepository: SalaryProjectRepository
) {
    operator fun invoke(statusJobBid: StatusJobBid): Flow<List<ISalaryProjectCompany>>{
        return salaryProjectRepository.getSalaryProjectsByStatus(statusJobBid)
    }
}