package com.example.data.repository

import com.example.data.local.dao.SalaryProjectDao
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.repository.SalaryProjectRepository
import kotlinx.coroutines.flow.Flow

class SalaryRepositoryImpl(
    private val salaryProjectDao: SalaryProjectDao
) : SalaryProjectRepository
{
    override fun getAllSalaryProjects(): Flow<List<ISalaryProjectCompany>> {
        TODO("Not yet implemented")
    }

    override fun getSalaryProjectsByCompanyId(companyId: Int): Flow<List<ISalaryProjectCompany>> {
        TODO("Not yet implemented")
    }

    override fun getSalaryProjectsByClientUserId(clientUserId: Int): Flow<List<ISalaryProjectCompany>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertSalaryProject(salaryProjectCompanyEntity: ISalaryProjectCompany) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSalaryProject(salaryProjectCompanyEntity: ISalaryProjectCompany) {
        TODO("Not yet implemented")
    }
}