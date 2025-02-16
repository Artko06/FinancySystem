package com.example.data.repository

import com.example.data.local.dao.CompanyDao
import com.example.data.local.entity.company.toDomain
import com.example.data.local.entity.company.toEntity
import com.example.domain.models.company.Company
import com.example.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CompanyRepositoryImpl(
    private val companyDao: CompanyDao
) : CompanyRepository
{
    override fun getAllCompanies(): Flow<List<Company>> {
        return companyDao.getAllCompanies().map { companies ->
            companies.map { company ->
                company.toDomain()
            }
        }
    }

    override fun getCompanyById(companyId: Int): Flow<Company?> {
        return companyDao.getCompanyById(companyId = companyId).map { company ->
            company?.toDomain()
        }
    }

    override suspend fun insertCompany(company: Company) {
        companyDao.insertCompany(company.toEntity())
    }

    override suspend fun deleteCompany(company: Company) {
        companyDao.deleteCompany(company.toEntity())
    }
}