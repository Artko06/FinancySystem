package com.example.domain.repository

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import kotlinx.coroutines.flow.Flow

interface SalaryProjectRepository {
    fun getAllSalaryProjects() : Flow<List<ISalaryProjectCompany>>
    fun getSalaryProjectsByCompanyId(companyId: Int) : Flow<List<ISalaryProjectCompany>>
    fun getSalaryProjectsByClientUserId(clientUserId: Int) : Flow<List<ISalaryProjectCompany>>
    suspend fun insertSalaryProject(salaryProjectCompanyEntity: ISalaryProjectCompany)
    suspend fun deleteSalaryProject(salaryProjectCompanyEntity: ISalaryProjectCompany)
}
