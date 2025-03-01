package com.example.domain.repository

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import kotlinx.coroutines.flow.Flow

interface SalaryProjectRepository {
    fun getAllSalaryProjects() : Flow<List<ISalaryProjectCompany>>
    fun getSalaryProjectsByCompanyId(companyId: Int) : Flow<List<ISalaryProjectCompany>>
    fun getSalaryProjectsByClientBaseUserId(clientBaseUserId: Int?) : Flow<List<ISalaryProjectCompany>>
    fun getSalaryProjectsByStatus(status: StatusJobBid) : Flow<List<ISalaryProjectCompany>>
    suspend fun changeClientSalaryProject(salaryProjectId: Int, clientBaseUserId: Int?)
    suspend fun changeStatusSalaryProject(salaryProjectCompany: SalaryProjectCompany, statusJobBid: StatusJobBid)
    suspend fun insertSalaryProject(salaryProjectCompany: ISalaryProjectCompany)
    suspend fun deleteSalaryProject(salaryProjectCompany: ISalaryProjectCompany)
    suspend fun deleteAllSalaryProjects()
}
